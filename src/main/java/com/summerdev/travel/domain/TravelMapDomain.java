package com.summerdev.travel.domain;

import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.responses.TravelMapResponse;
import org.springframework.stereotype.Service;

public interface TravelMapDomain {
    TravelMapResponse getTravelMap(TravelMapRequest request);
}
