package com.summerdev.travel.services.api.hotellook;

import com.summerdev.travel.requests.api.hotellook.HotelLookRequest;
import com.summerdev.travel.responses.api.hotellook.HotelLookHotelsResponse;

public interface HotelLookService {
    HotelLookHotelsResponse get(HotelLookRequest request);
}
