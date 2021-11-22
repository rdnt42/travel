package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.response.TravelMapResponse;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 18:55
 */
public interface RouteService {
    TravelMapResponse getTravelMap(GeoName departureCity, TravelComfortType comfortType);
}
