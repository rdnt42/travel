package com.summerdev.travel.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 14:22
 */
@Embeddable
public class TutuRoutePk implements Serializable {

    @Column(name = "departure_station_id")
    private Long departureStationId;

    @Column(name = "arrival_station_id")
    private Long arrivalStationId;

    public Long getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Long departureStationId) {
        this.departureStationId = departureStationId;
    }

    public Long getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Long arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }
}
