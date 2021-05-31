package com.summerdev.travel.service;

import com.summerdev.travel.entity.TrainIfo;
import com.summerdev.travel.entity.TutuRoute;
import com.summerdev.travel.entity.TutuStation;
import com.summerdev.travel.repository.TrainInfoRepository;
import com.summerdev.travel.repository.TutuRouteRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import com.summerdev.travel.response.api.tutu.TutuRailwayCarriageResponse;
import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.response.api.tutu.TutuTripItemResponse;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.summerdev.travel.entity.SeatType.*;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 18:56
 */
@Service
public class TutuRouteServiceImpl implements TutuRouteService {
    private final Logger logger = LoggerFactory.getLogger(TutuRouteServiceImpl.class);

    private final TutuRouteRepository tutuRouteRepository;
    private final TutuService tutuService;
    private final TutuStationRepository tutuStationRepository;
    private final TrainInfoRepository trainInfoRepository;

    public TutuRouteServiceImpl(TutuRouteRepository tutuRouteRepository, TutuService tutuService,
                                TutuStationRepository tutuStationRepository, TrainInfoRepository trainInfoRepository) {
        this.tutuRouteRepository = tutuRouteRepository;
        this.tutuService = tutuService;
        this.tutuStationRepository = tutuStationRepository;
        this.trainInfoRepository = trainInfoRepository;
    }

    @Override
    public void updateTrainsInfo() {
        List<TrainIfo> oldData = trainInfoRepository.findAll();
        long updatedCount = 0;

        List<TutuRoute> routes = tutuRouteRepository.findAll();
        for (TutuRoute route : routes) {
            try {
                List<TrainIfo> updatedList = updateTrainInfo(route);
                updatedCount += updatedList.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        trainInfoRepository.deleteAll(oldData);

        logger.info("Trains info data updated. Updated count: {}, deleted count: {}", updatedCount, oldData.size());
    }

    private List<TrainIfo> updateTrainInfo(TutuRoute route) {
        TutuTrainsResponse response = tutuService.getTrainsResponse(route.getDepartureStation(), route.getArrivalStation());

        if (response == null) return new ArrayList<>();

        List<TrainIfo> trainIfos = new ArrayList<>();
        for (TutuTripItemResponse trip : response.getTrips()) {
            for (TutuRailwayCarriageResponse category : trip.getCategories()) {

                TrainIfo trainIfo = getTrainInfo(trip, category);
                if (trainIfo != null) {
                    trainIfos.add(trainIfo);
                }
            }
        }

        if (trainIfos.isEmpty()) return new ArrayList<>();

        Map<Long, Optional<TrainIfo>> minCostTrains = trainIfos.stream()
                .collect(Collectors.groupingBy(TrainIfo::getSeatTypeId,
                        Collectors.minBy(Comparator.comparing(TrainIfo::getCost))));

        List<TrainIfo> filteredList = minCostTrains.values().stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        trainInfoRepository.saveAll(filteredList);

        return filteredList;
    }

    private TrainIfo getTrainInfo(TutuTripItemResponse trip, TutuRailwayCarriageResponse category) {
        TrainIfo trainIfo = new TrainIfo();
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
        Long seatTypeId = null;
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
