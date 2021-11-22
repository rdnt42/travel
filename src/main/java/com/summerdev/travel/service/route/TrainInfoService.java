package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.entity.route.TrainInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 31.05.2021
 * Time: 21:24
 */
public interface TrainInfoService {
    List<TrainInfo> getMapInfo(GeoName departureCity, TravelComfortType comfortType);
}
