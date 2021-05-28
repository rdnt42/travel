package com.summerdev.travel.controller;

import com.summerdev.travel.request.TravelMapRequest;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelsResponse;
import com.summerdev.travel.service.api.hotellook.HotelLookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class HotelLookController {

    private final HotelLookService hotelLookService;

    public HotelLookController(HotelLookService hotelLookService) {
        this.hotelLookService = hotelLookService;
    }

    @PostMapping("/v1/hotel_look")
    @ResponseBody
    public List<HotelLookHotelsResponse> get(@RequestBody TravelMapRequest request) {
        return hotelLookService.getHotelsInfo(request);
    }
}
