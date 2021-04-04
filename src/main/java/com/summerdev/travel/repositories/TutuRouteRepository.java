package com.summerdev.travel.repositories;

import com.summerdev.travel.entities.TutuRoute;
import com.summerdev.travel.entities.TutuStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutuRouteRepository extends JpaRepository<TutuRoute, Long> {
    List<TutuRoute> findByDepartureStation(TutuStation departureStation);
    List<TutuRoute> findByDepartureStationAndPopularityGreaterThanEqual(TutuStation departureStation, Long rating);

}
