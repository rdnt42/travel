package com.summerdev.travel.controller;

import com.summerdev.travel.response.TravelMapResponse;
import com.summerdev.travel.service.TravelMapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TravelController {

    private final TravelMapService travelMapService;

    public TravelController(TravelMapService travelMapDomain) {
        this.travelMapService = travelMapDomain;
    }

    @GetMapping("/v1/travel_map")
    public TravelMapResponse getTravelMap(@RequestParam String departureCity,
                                          @RequestParam Long maxCost,
                                          @RequestParam Integer comfortTypeId,
                                          @RequestParam Long days) {
        return travelMapService.getTravelMap(departureCity, maxCost, comfortTypeId, days);
    }

}
