package com.summerdev.travel.services.api.tutu;

import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.requests.api.tutu.TutuRequest;
import com.summerdev.travel.responses.api.tutu.TutuTrainsResponse;

import java.util.List;

public interface TutuService {
    /**
     * Получение списка информации по направлениям
     * @param request
     * @return
     */
    List<TutuTrainsResponse> getTrainsInfo(TravelMapRequest request);

    /**
     * Запрос на API tutu.ru
     * @param request
     * @return
     */
    TutuTrainsResponse get(TutuRequest request);
}
