package com.summerdev.travel.service.api.aviasales;

import com.summerdev.travel.request.api.aviasales.AviaSalesRequest;
import com.summerdev.travel.response.api.aviasales.AviaSalesMainResponse;

public interface AviaSalesService {

    AviaSalesMainResponse get(AviaSalesRequest request);

}
