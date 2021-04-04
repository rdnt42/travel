package com.summerdev.travel.controllers;

import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.requests.api.aviasales.AviaSalesRequest;
import com.summerdev.travel.responses.api.aviasales.AviaSalesMainResponse;
import com.summerdev.travel.services.api.aviasales.AviaSalesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class AviaSalesController {

    private final AviaSalesService aviaSalesService;

    public AviaSalesController(AviaSalesService aviaSalesService) {
        this.aviaSalesService = aviaSalesService;
    }

//    public AviaSalesController(HotelLookService hotelLookService) {
//        this.hotelLookService = hotelLookService;
//    }


    @PostMapping("/v1/aviasales")
    @ResponseBody
    public AviaSalesMainResponse getCheapTikets(@RequestBody AviaSalesRequest request) {
        return aviaSalesService.get(request);
    }
}
