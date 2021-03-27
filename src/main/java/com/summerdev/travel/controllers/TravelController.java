package com.summerdev.travel.controllers;

import com.summerdev.travel.domain.TravelMapDomain;
import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.responses.TravelMapResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TravelController {

    private TravelMapDomain travelMapDomain;

    public TravelController(TravelMapDomain travelMapDomain) {
        this.travelMapDomain = travelMapDomain;
    }

    @GetMapping("/v1/test")
    @ResponseBody
    public String getTest() {
        return "test";
    }
    @PostMapping("/v1/travel_map")
    @ResponseBody
    public TravelMapResponse getTravelMap(@RequestBody TravelMapRequest request) {
        return travelMapDomain.getTravelMap(request);
    }
}
