package com.summerdev.travel.controller;

import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.service.RouteService;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TutuController {

    private final TutuService tutuService;
    private final RouteService tutuRouteService;

    public TutuController(TutuService tutuService, RouteService tutuRouteService) {
        this.tutuService = tutuService;
        this.tutuRouteService = tutuRouteService;
    }

    @GetMapping("/v1/tutu")
    public List<TutuTrainsResponse> get(@RequestParam String departureCity) {
        return tutuService.getTrainsInfo(departureCity);
    }

    @GetMapping("/v1/routes/update")
    public void updateRoutes() {
        tutuRouteService.updateTrainsInfo();
    }

}
