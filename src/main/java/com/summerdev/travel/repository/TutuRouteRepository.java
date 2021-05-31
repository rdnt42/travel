package com.summerdev.travel.repository;

import com.summerdev.travel.entity.TutuRoute;
import com.summerdev.travel.entity.TutuStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutuRouteRepository extends JpaRepository<TutuRoute, Long> {
    List<TutuRoute> findByDepartureStation(TutuStation departureStation);
}
