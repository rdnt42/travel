package com.summerdev.travel.service.api.tutu;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TutuStation;
import com.summerdev.travel.request.api.tutu.TutuRequest;
import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;

import java.util.List;

public interface TutuService {
    /**
     * Получение списка информации по направлениям
     * @param departureCity
     * @return
     */
    List<TutuTrainsResponse> getTrainsInfo(String departureCity);
    List<TutuTrainsResponse> getTrainsInfo(GeoName departureCity);
    /**
     * Запрос на API tutu.ru
     * @param arrivalStation
     * @param departureStation
     * @return
     */
    TutuTrainsResponse getTrainsResponse(int departureStation, int arrivalStation);
    TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation);

    String getUnparsedTrainsResponse(int departureStation, int arrivalStation);
}
