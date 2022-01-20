package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.hotel.HotelInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 01.06.2021
 * Time: 20:39
 */
public interface HotelInfoService {
    List<HotelInfo> getInfo(List<GeoNameData> cities, String comfortType);
}
