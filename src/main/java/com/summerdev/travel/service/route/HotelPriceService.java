package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.hotel.HotelPrice;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 01.06.2021
 * Time: 20:39
 */
public interface HotelPriceService {
    List<HotelPrice> getHotelsPricesForTrip(List<GeoNameData> cities, Long totalBudget, ComfortType comfortType);
}
