package com.summerdev.travel.service;

import com.summerdev.travel.response.TravelMapResponse;

public interface TravelMapService {
    TravelMapResponse getTravelMap(String departureCityName, Long maxCost,
                                   String travelComfortType, Long days);
}
