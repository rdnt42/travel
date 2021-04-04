package com.summerdev.travel.repositories;

import com.summerdev.travel.entities.TutuStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutuStationRepository extends JpaRepository<TutuStation, Long> {
    List<TutuStation> findByStationNameStartsWith(String name);

}
