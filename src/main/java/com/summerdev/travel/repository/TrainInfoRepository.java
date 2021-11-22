package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.route.TrainInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 19:26
 */
public interface TrainInfoRepository extends JpaRepository<TrainInfo, Long> {
    List<TrainInfo> findAllByDepartureCity(GeoName departureCity);

    List<TrainInfo> findAllByDepartureCityAndSeatTypeIdIn(
            GeoName departureCity, List<Long> seatTypeIds);
}
