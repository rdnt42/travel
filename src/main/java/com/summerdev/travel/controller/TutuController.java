package com.summerdev.travel.controller;

import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.service.TutuRouteService;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TutuController {

    private final TutuService tutuService;
    private final TutuRouteService tutuRouteService;

    public TutuController(TutuService tutuService, TutuRouteService tutuRouteService) {
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
