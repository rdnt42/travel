package com.summerdev.travel.entity.tutu;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TutuStationDirectory {
    @Id
    private Long stationId;

    private String stationName;

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
}
