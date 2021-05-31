package com.summerdev.travel.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tutu_stations")
public class TutuStation implements Serializable {
    @Id
    private Long stationId;

    private String stationName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "geo_name_id")
    private GeoName geoName;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public GeoName getGeoName() {
        return geoName;
    }

    public void setGeoName(GeoName geoName) {
        this.geoName = geoName;
    }
}
