package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.train.TrainPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 22:25
 */
public interface TrainPriceRepository extends JpaRepository<TrainPrice, Long> {
    @Query("select t from TrainPrice t where t.trainInfo.departureCity = ?1 and t.comfortType = ?2 and t.cost < ?3")
    List<TrainPrice> findAllCheapPrices(GeoNameData departureCity, ComfortType comfortType, Double cost);
}
