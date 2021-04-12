package com.summerdev.travel.services.api.aviasales;

import com.summerdev.travel.requests.api.aviasales.AviaSalesRequest;
import com.summerdev.travel.responses.api.aviasales.AviaSalesMainResponse;

public interface AviaSalesService {

    AviaSalesMainResponse get(AviaSalesRequest request);

}
