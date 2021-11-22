package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.entity.route.HotelInfo;
import com.summerdev.travel.entity.route.TrainInfo;
import com.summerdev.travel.response.TravelMapItemResponse;
import com.summerdev.travel.response.TravelMapResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 18:56
 */
@Service
public class RouteServiceImpl implements RouteService {
    private final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    private final TrainInfoServiceImpl trainInfoService;
    private final HotelInfoServiceImpl hotelInfoService;

    public RouteServiceImpl(TrainInfoServiceImpl trainInfoService, HotelInfoServiceImpl hotelInfoService) {
        this.trainInfoService = trainInfoService;
        this.hotelInfoService = hotelInfoService;
    }


    @Override
    public TravelMapResponse getTravelMap(GeoName departureCity, TravelComfortType comfortType) {
        TravelMapResponse response = new TravelMapResponse();
        List<TrainInfo> trainInfos = trainInfoService.getMapInfo(departureCity, comfortType);

        Set<GeoName> arrivalCities = trainInfos.stream()
                .map(TrainInfo::getArrivalCity)
                .collect(Collectors.toSet());

        // Add data from aviasales here

        List<HotelInfo> hotelInfos = hotelInfoService.getInfo(new ArrayList<>(arrivalCities), comfortType);

        for (TrainInfo trainInfo : trainInfos) {
            HotelInfo hotelInfo = hotelInfos.stream()
                    .filter(hotel -> hotel.getCity().getId().equals(
                            trainInfo.getArrivalCity().getId()))
                    .findAny()
                    .orElse(null);

            TravelMapItemResponse itemResponse = new TravelMapItemResponse(trainInfo, hotelInfo);
            response.addItem(itemResponse);
        }

        return response;
    }
}
