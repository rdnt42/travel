package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TrainInfo;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.entity.TutuRoute;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 31.05.2021
 * Time: 21:24
 */
public interface TrainInfoService {
    List<TrainInfo> getInfoByDepartAndComfortType(GeoName departureCity, TravelComfortType comfortType);

    List<TrainInfo> createTrainsInfo(TutuRoute route);

    void deleteOldTrainsInfo(List<TrainInfo> oldData);

    List<TrainInfo> getAllData();
}
