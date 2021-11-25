package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.entity.route.TrainInfo;
import com.summerdev.travel.repository.TrainInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TrainInfoServiceImpl implements ITravelInfoService<TrainInfo>, TrainInfoService {
    Logger logger = LoggerFactory.getLogger(TrainInfoServiceImpl.class);

    private final List<Long> cheapList = Arrays.asList(SEAT_TYPE_ID_COMMON, SEAT_TYPE_ID_ECONOMY, SEAT_TYPE_ID_SEDENTARY);
    private final List<Long> comfortList = Arrays.asList(SEAT_TYPE_ID_COUPE, SEAT_TYPE_ID_SOFT);
    private final List<Long> luxuryList = Collections.singletonList(SEAT_TYPE_ID_LUX);

    private final TrainInfoRepository trainInfoRepository;

    public TrainInfoServiceImpl(TrainInfoRepository trainInfoRepository) {
        this.trainInfoRepository = trainInfoRepository;
    }

    @Override
    public List<TrainInfo> getAllActualInfo() {
        return trainInfoRepository.findAll();
    }



    @Override
    public List<TrainInfo> getMapInfo(GeoName departureCity, TravelComfortType comfortType) {
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

        return trainInfos.stream()
                .collect(Collectors.groupingBy(s -> s.getSeatTypeId() + "-" + s.getArrivalCity().getGeoName(),
                        Collectors.minBy(Comparator.comparing(TrainInfo::getCost))))
                .values().stream()
                .flatMap(Optional::stream)
                .sorted(Comparator.comparing(s -> s.getArrivalCity().getGeoName()))
                .collect(Collectors.toList());
    }


}
