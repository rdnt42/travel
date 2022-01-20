package com.summerdev.travel.entity.tutu;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Table(name = "tutu_routes")
@Entity
public class TutuRoute implements Serializable {
    @Id
    @Column(name = "tutu_route_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_station_id", insertable = false, updatable = false)
    private TutuStation departureStation;

    @ManyToOne
    @JoinColumn(name = "arrival_station_id", insertable = false, updatable = false)
    private TutuStation arrivalStation;

}

