package com.summerdev.travel.controllers;

import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.responses.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.services.api.tutu.TutuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TutuController {

    private final TutuService tutuService;

    public TutuController(TutuService tutuService) {
        this.tutuService = tutuService;
    }

    @PostMapping("/v1/tutu")
    @ResponseBody
    public List<TutuTrainsResponse> get(@RequestBody TravelMapRequest request) {
        return tutuService.getTrainsInfo(request);
    }
}
