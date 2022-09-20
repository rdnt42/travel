package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.hotel.HotelPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 21:15
 */
public interface HotelPriceRepository extends JpaRepository<HotelPrice, Long> {
    @Query("select t from HotelPrice t where t.hotelInfo.city in ?1 and t.comfortType = ?2 and t.cost < ?3")
    List<HotelPrice> findAllCheapPrices(List<GeoNameData> cities, ComfortType comfortType, Double cost);

    @Query("select t from HotelPrice t where t.hotelInfo.city.geoNameRu in ?1 and t.comfortType = ?2 and t.cost < ?3")
    List<HotelPrice> findAllCheapPrices(Set<String> cities, ComfortType comfortType, Double cost);
}