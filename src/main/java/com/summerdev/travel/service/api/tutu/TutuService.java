package com.summerdev.travel.service.api.tutu;

import com.summerdev.travel.requests.api.tutu.TutuRequest;
import com.summerdev.travel.responses.api.tutu.TutuTrainsResponse;

public interface TutuService {
    TutuTrainsResponse get(TutuRequest request);
}
