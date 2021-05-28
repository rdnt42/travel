package com.summerdev.travel.service;

import com.summerdev.travel.response.TravelMapResponse;
import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelMapServiceImpl implements TravelMapService {

    private static final Logger log = LoggerFactory.getLogger(TravelMapServiceImpl.class);

    private TutuService tutuService;

    public TravelMapServiceImpl(TutuService tutuService) {
        this.tutuService = tutuService;
    }

    @Override
    public TravelMapResponse getTravelMap(String departureCity, Long maxCost) {
        List<TutuTrainsResponse> tutuTrainsResponses = tutuService.getTrainsInfo(departureCity);

        return null;
    }
}
