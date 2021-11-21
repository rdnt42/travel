package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TutuStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutuStationRepository extends JpaRepository<TutuStation, Long> {
    List<TutuStation> findByStationNameStartsWith(String name);

    List<TutuStation> findByGeoName(GeoName geoName);
}
