package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.*;
import com.summerdev.travel.repository.TrainInfoRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import com.summerdev.travel.response.api.tutu.TutuRailwayCarriageResponse;
import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.response.api.tutu.TutuTripItemResponse;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.summerdev.travel.entity.SeatType.*;
import static com.summerdev.travel.entity.TravelComfortType.*;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 31.05.2021
 * Time: 21:24
 */
@Service
public class TrainInfoServiceImpl implements TrainInfoService {
    private final List<Long> cheapList = Arrays.asList(SEAT_TYPE_ID_COMMON, SEAT_TYPE_ID_PLAZCARD, SEAT_TYPE_ID_SEDENTARY);
    private final List<Long> comfortList = Arrays.asList(SEAT_TYPE_ID_COUPE, SEAT_TYPE_ID_SOFT);
    private final List<Long> luxuryList = Collections.singletonList(SEAT_TYPE_ID_LUX);


    private final TutuService tutuService;
    private final TrainInfoRepository trainInfoRepository;
    private final TutuStationRepository tutuStationRepository;

    public TrainInfoServiceImpl(TutuService tutuService, TrainInfoRepository trainInfoRepository,
                                TutuStationRepository tutuStationRepository) {
        this.tutuService = tutuService;
        this.trainInfoRepository = trainInfoRepository;
        this.tutuStationRepository = tutuStationRepository;
    }

    @Override
    public  List<TrainInfo> createTrainsInfo(TutuRoute route) {
        TutuTrainsResponse response = tutuService.getTrainsResponse(route.getDepartureStation(), route.getArrivalStation());

        if (response == null) return new ArrayList<>();

        List<TrainInfo> trainInfos = new ArrayList<>();
        for (TutuTripItemResponse trip : response.getTrips()) {
            for (TutuRailwayCarriageResponse category : trip.getCategories()) {

                TrainInfo trainIfo = getTrainInfo(trip, category);
                if (trainIfo != null) {
                    trainInfos.add(trainIfo);
                }
            }
        }

        if (trainInfos.isEmpty()) return new ArrayList<>();

        Map<Long, Optional<TrainInfo>> minCostTrains = trainInfos.stream()
                .collect(Collectors.groupingBy(TrainInfo::getSeatTypeId,
                        Collectors.minBy(Comparator.comparing(TrainInfo::getCost))));

        List<TrainInfo> filteredList = minCostTrains.values().stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        trainInfoRepository.saveAll(filteredList);

        return filteredList;
    }

    @Override
    public List<TrainInfo> getInfoByDepartAndComfortType(GeoName departureCity, TravelComfortType comfortType) {
        List<TrainInfo> trainInfos = new ArrayList<>();
        Long comfortTypeId = comfortType.getId();

        if (comfortTypeId.equals(COMFORT_TYPE_CHEAP)) {
            trainInfos = trainInfoRepository.findAllByDepartureCityAndSeatTypeIdIn(departureCity, cheapList);
        } else if (comfortTypeId.equals(COMFORT_TYPE_COMFORT)) {
            trainInfos = trainInfoRepository.findAllByDepartureCityAndSeatTypeIdIn(departureCity, comfortList);
        } else if (comfortTypeId.equals(COMFORT_TYPE_LUXURY)) {
            trainInfos = trainInfoRepository.findAllByDepartureCityAndSeatTypeIdIn(departureCity, luxuryList);
        }

        if (trainInfos.isEmpty()) {
            return trainInfos;
        }

        return  trainInfos.stream()
                .collect(Collectors.groupingBy(s->s.getSeatTypeId() + "-" + s.getArrivalCity().getGeoName(),
                        Collectors.minBy(Comparator.comparing(TrainInfo::getCost))))
                .values().stream()
                .flatMap(Optional::stream)
                .sorted(Comparator.comparing(s->s.getArrivalCity().getGeoName()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOldTrainsInfo(List<TrainInfo> oldData) {
        trainInfoRepository.deleteAll(oldData);
    }

    @Override
    public List<TrainInfo> getAllData() {
        return trainInfoRepository.findAll();
    }

    private TrainInfo getTrainInfo(TutuTripItemResponse trip, TutuRailwayCarriageResponse category) {
        TrainInfo trainIfo = new TrainInfo();
        trainIfo.setTravelTime(trip.getTravelTimeInSeconds());

        TutuStation arrivalStation = tutuStationRepository.findById(trip.getArrivalStation())
                .orElse(null);
        TutuStation departureStation = tutuStationRepository.findById(trip.getDepartureStation())
                .orElse(null);

        if (arrivalStation == null || departureStation == null) return null;

        trainIfo.setDepartureCity(departureStation.getGeoName());
        trainIfo.setArrivalCity(arrivalStation.getGeoName());

        trainIfo.setCost((long) category.getPrice());
        trainIfo.setSeatTypeId(getSeatTypeId(category.getType()));

        return trainIfo;
    }

    // FIXME
    private Long getSeatTypeId(String category) {
        Long seatTypeId;
        switch (category) {
            case "plazcard":
                seatTypeId = SEAT_TYPE_ID_PLAZCARD;
                break;
            case "coupe":
                seatTypeId = SEAT_TYPE_ID_COUPE;
                break;
            case "sedentary":
                seatTypeId = SEAT_TYPE_ID_SEDENTARY;
                break;
            case "lux":
                seatTypeId = SEAT_TYPE_ID_LUX;
                break;
            case "soft":
                seatTypeId = SEAT_TYPE_ID_SOFT;
                break;

            default:
                seatTypeId = SEAT_TYPE_ID_COMMON;
        }

        return seatTypeId;
    }
}
