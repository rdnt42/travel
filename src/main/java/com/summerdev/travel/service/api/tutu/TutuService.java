package com.summerdev.travel.service.api.tutu;

import com.summerdev.travel.request.api.tutu.TutuRequest;
import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;

import java.util.List;

public interface TutuService {
    /**
     * Получение списка информации по направлениям
     * @param request
     * @return
     */
    List<TutuTrainsResponse> getTrainsInfo(String departureCity);

    /**
     * Запрос на API tutu.ru
     * @param request
     * @return
     */
    TutuTrainsResponse get(TutuRequest request);
}
