package com.summerdev.travel.service;

import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.requests.api.tutu.TutuRequest;
import com.summerdev.travel.responses.TravelMapResponse;
import com.summerdev.travel.responses.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TravelMapServiceImpl implements TravelMapService {

    private static final Logger log = LoggerFactory.getLogger(TravelMapServiceImpl.class);

    private TutuService tutuService;

    public TravelMapServiceImpl(TutuService tutuService) {
        this.tutuService = tutuService;
    }

    @Override
    public TravelMapResponse getTravelMap(TravelMapRequest request) {
        TutuTrainsResponse tutuTrainsResponse = tutuService.get(new TutuRequest(2000000, 2064130));

        return null;
    }
}
