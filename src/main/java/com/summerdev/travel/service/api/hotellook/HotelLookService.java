package com.summerdev.travel.service.api.hotellook;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.request.TravelMapRequest;
import com.summerdev.travel.request.api.hotellook.HotelLookRequest;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelsResponse;

import java.util.Date;
import java.util.List;

public interface HotelLookService {
    /**
     * Запрос на API hotellook.ru через travelpayouts
     * https://support.travelpayouts.com/hc/ru/articles/115000343268-API-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85-%D0%BE%D1%82%D0%B5%D0%BB%D0%B5%D0%B9
     *
     * @param request фильтр для поиска отеля в указанном городе
     * @return список отелей, соответсвующих фильтру
     */
    HotelLookHotelsResponse getHotelsResponse(HotelLookRequest request);

    /**
     * Информация обо всех отелях в указанном городе
     *
     * @param city      город поиска
     * @param startDate дата заезда
     * @param endDate   дата выезда
     * @return список отелей, соответсующих фильтру
     */
    HotelLookHotelsResponse getHotelsInfo(GeoName city, Date startDate, Date endDate);

    /**
     * метод для тестирования API
     */
    List<HotelLookHotelsResponse> getHotelsInfo(TravelMapRequest request);
}
