package com.summerdev.travel.services.api.aviasales;

import com.summerdev.travel.requests.api.aviasales.AviaSalesRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface AviaSalesService {

    OutputStream get(AviaSalesRequest request) throws IOException;

}
