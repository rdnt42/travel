package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.enums.ComfortTypes;
import com.summerdev.travel.entity.train.TrainPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 22:25
 */
public interface TrainPriceRepository extends JpaRepository<TrainPrice, Long> {
    List<TrainPrice> findAllByTrainInfoDepartureCityAndCostLessThanAndComfortType(
            GeoNameData departureCity, Double cost, ComfortTypes comfortType);
}
