package com.summerdev.travel.service;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TrainInfo;
import com.summerdev.travel.entity.TravelComfortType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 31.05.2021
 * Time: 21:24
 */
public interface TrainInfoService {
    List<TrainInfo> getInfoByDepartAndComfortType(GeoName departureCity, TravelComfortType comfortType);
}
