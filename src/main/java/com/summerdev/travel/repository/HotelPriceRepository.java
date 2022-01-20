package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.hotel.HotelPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 21:15
 */
public interface HotelPriceRepository extends JpaRepository<HotelPrice, Long> {
    List<HotelPrice> findAllByHotelInfoCityInAndCostLessThanAndComfortType(
            List<GeoNameData> cities, Double cost, ComfortType comfortType);

}