package com.summerdev.travel.service;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TrainInfo;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.repository.TrainInfoRepository;
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


    private final TrainInfoRepository trainInfoRepository;

    public TrainInfoServiceImpl(TrainInfoRepository trainInfoRepository) {
        this.trainInfoRepository = trainInfoRepository;
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
}
