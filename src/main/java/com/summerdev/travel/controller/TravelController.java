package com.summerdev.travel.controller;

import com.summerdev.travel.service.TravelMapService;
import com.summerdev.travel.response.TravelMapResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TravelController {

    private final TravelMapService travelMapDomain;

    public TravelController(TravelMapService travelMapDomain) {
        this.travelMapDomain = travelMapDomain;
    }

    @GetMapping("/v1/travel_map")
    public TravelMapResponse getTravelMap(@RequestParam String departureCity,
                                          @RequestParam Long maxCost) {
        return travelMapDomain.getTravelMap(departureCity, maxCost);
    }

}
