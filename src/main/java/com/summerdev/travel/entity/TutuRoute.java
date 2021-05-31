package com.summerdev.travel.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tutu_routes")
public class TutuRoute implements Serializable {
    @EmbeddedId
    private TutuRoutePk id;

    @ManyToOne
    @JoinColumn(name = "departure_station_id", insertable = false, updatable = false)
    private TutuStation departureStation;

    @ManyToOne
    @JoinColumn(name = "arrival_station_id", insertable = false, updatable = false)
    private TutuStation arrivalStation;

    public TutuRoutePk getId() {
        return id;
    }

    public void setId(TutuRoutePk id) {
        this.id = id;
    }

    public TutuStation getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(TutuStation departureStation) {
        this.departureStation = departureStation;
    }

    public TutuStation getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(TutuStation arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

}
