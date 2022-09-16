package com.summerdev.travel.service;

import com.summerdev.travel.response.TravelMapResponse;

public interface TravelMapService {
    TravelMapResponse getTravelMap(String departureCityName, Long maxCost,
                                   Integer comfortTypeId, Long days);
}
