package com.summerdev.travel.controllers;

import com.summerdev.travel.domain.TravelMapDomain;
import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.responses.TravelMapResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class TravelController {

    private TravelMapDomain travelMapDomain;

    public TravelController(TravelMapDomain travelMapDomain) {
        this.travelMapDomain = travelMapDomain;
    }

    @PostMapping("/travel_map")
    public TravelMapResponse getTravelMap(TravelMapRequest request) {
        return travelMapDomain.getTravelMap(request);
    }
}
