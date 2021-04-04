package com.summerdev.travel.services.api.hotellook;

import com.summerdev.travel.entities.TutuStation;
import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.requests.api.hotellook.HotelLookRequest;
import com.summerdev.travel.responses.api.hotellook.HotelLookHotelsResponse;

import java.util.List;

public interface HotelLookService {
    HotelLookHotelsResponse get(HotelLookRequest request);

    List<HotelLookHotelsResponse> getHotelsInfo(TravelMapRequest request, List<TutuStation> arrivalStations);

    List<HotelLookHotelsResponse> getHotelsInfo(TravelMapRequest request);
}
