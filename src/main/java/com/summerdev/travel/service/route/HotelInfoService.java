package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.HotelInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 01.06.2021
 * Time: 20:39
 */
public interface HotelInfoService {
    List<HotelInfo> getAll();

    void deleteOldHotelsData(List<HotelInfo> oldData);
    List<HotelInfo> createHotelsInfo(GeoName city);
}
