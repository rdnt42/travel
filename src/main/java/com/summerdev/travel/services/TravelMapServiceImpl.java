package com.summerdev.travel.services;

import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.responses.TravelMapResponse;
import com.summerdev.travel.responses.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.services.api.tutu.TutuService;
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
    public TravelMapResponse getTravelMap(TravelMapRequest request) {
        List<TutuTrainsResponse> tutuTrainsResponses = tutuService.getTrainsInfo(request);

        return null;
    }
}
