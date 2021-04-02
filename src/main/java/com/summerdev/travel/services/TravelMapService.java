package com.summerdev.travel.services;

import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.responses.TravelMapResponse;

public interface TravelMapService {
    TravelMapResponse getTravelMap(TravelMapRequest request);
}
