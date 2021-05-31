package com.summerdev.travel.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 19:01
 */
@Entity
@Table(name = "trains_info")
public class TrainIfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_info_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private GeoName departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private GeoName arrivalCity;

    private Long travelTime;

    @Column(name = "seat_type_id")
    private Long seatTypeId = 0L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_type_id", insertable = false, updatable = false)
    private SeatType seatType;

    private Long cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeoName getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(GeoName departureCity) {
        this.departureCity = departureCity;
    }

    public GeoName getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(GeoName arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Long getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Long travelTime) {
        this.travelTime = travelTime;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Long seatTypeId) {
        this.seatTypeId = seatTypeId;
    }
}
