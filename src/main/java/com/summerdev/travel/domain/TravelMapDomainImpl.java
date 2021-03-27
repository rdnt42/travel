package com.summerdev.travel.domain;

import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.responses.TravelMapResponse;
import org.springframework.stereotype.Service;

@Service
public class TravelMapDomainImpl implements TravelMapDomain {

    @Override
    public TravelMapResponse getTravelMap(TravelMapRequest request) {
        return null;

    }
}
