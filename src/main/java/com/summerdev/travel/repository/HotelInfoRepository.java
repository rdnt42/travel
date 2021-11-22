package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.route.HotelInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 01.06.2021
 * Time: 23:24
 */
public interface HotelInfoRepository extends JpaRepository<HotelInfo, Long> {
    List<HotelInfo> findAllByIsActualDataIsTrue();

    List<HotelInfo> findAllByCityInAndStarsIn(List<GeoName> cities, List<Long> stars);
}
