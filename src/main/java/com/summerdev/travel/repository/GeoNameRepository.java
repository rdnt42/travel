package com.summerdev.travel.repository;

import com.summerdev.travel.entity.GeoName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 29.05.2021
 * Time: 17:04
 */
public interface GeoNameRepository extends JpaRepository<GeoName, Long> {
    Optional<GeoName> findDistinctFirstByGeoNameRu(String geoNameRu);
}
