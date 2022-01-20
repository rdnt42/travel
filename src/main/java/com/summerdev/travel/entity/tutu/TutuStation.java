package com.summerdev.travel.entity.tutu;

import com.summerdev.travel.entity.GeoNameData;

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
    private GeoNameData geoName;

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

    public GeoNameData getGeoName() {
        return geoName;
    }

    public void setGeoName(GeoNameData geoName) {
        this.geoName = geoName;
    }
}
