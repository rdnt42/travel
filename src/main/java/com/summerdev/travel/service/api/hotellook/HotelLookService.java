package com.summerdev.travel.service.api.hotellook;

import com.summerdev.travel.entity.TutuStation;
import com.summerdev.travel.request.TravelMapRequest;
import com.summerdev.travel.request.api.hotellook.HotelLookRequest;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelsResponse;

import java.util.List;

public interface HotelLookService {
    HotelLookHotelsResponse get(HotelLookRequest request);

    List<HotelLookHotelsResponse> getHotelsInfo(TravelMapRequest request, List<TutuStation> arrivalStations);

    List<HotelLookHotelsResponse> getHotelsInfo(TravelMapRequest request);
}
