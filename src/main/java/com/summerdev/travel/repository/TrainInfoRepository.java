package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.train.TrainInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 19:26
 */
public interface TrainInfoRepository extends JpaRepository<TrainInfo, Long> {
    List<TrainInfo> findAllByDepartureCity(GeoNameData departureCity);
}
