package com.summerdev.travel.controller;

import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TutuController {

    private final TutuService tutuService;

    public TutuController(TutuService tutuService) {
        this.tutuService = tutuService;
    }

    @GetMapping("/v1/tutu")
    public List<TutuTrainsResponse> get(@RequestParam String departureCity) {
        return tutuService.getTrainsInfo(departureCity);
    }

}
