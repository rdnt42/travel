package com.summerdev.travel.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tutu_routes")
public class TutuRoute implements Serializable {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_station_id")
    private TutuStation departureStation;

    @ManyToOne
    @JoinColumn(name = "arrival_station_id")
    private TutuStation arrivalStation;

    private Long popularity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }
}
