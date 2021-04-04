package com.summerdev.travel.services.api.aviasales;

import com.summerdev.travel.entities.TutuStation;
import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.requests.api.aviasales.AviaSalesRequest;
import com.summerdev.travel.requests.api.hotellook.HotelLookRequest;
import com.summerdev.travel.responses.api.aviasales.AviaSalesMainResponse;
import com.summerdev.travel.responses.api.hotellook.HotelLookHotelsResponse;

import java.util.List;

public interface AviaSalesService {

    AviaSalesMainResponse get(AviaSalesRequest request);

}
