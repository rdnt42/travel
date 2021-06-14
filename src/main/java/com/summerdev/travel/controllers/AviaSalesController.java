package com.summerdev.travel.controllers;

import com.summerdev.travel.requests.api.aviasales.AviaSalesRequest;
import com.summerdev.travel.services.api.aviasales.AviaSalesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;

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
    public OutputStream getCheapTikets(@RequestBody AviaSalesRequest request) throws IOException {
        return aviaSalesService.get(request);
    }
}
