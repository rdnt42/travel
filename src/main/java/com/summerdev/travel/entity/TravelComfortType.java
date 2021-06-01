package com.summerdev.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 31.05.2021
 * Time: 21:18
 */
@Entity
@Table(name = "travel_comfort_types")
public class TravelComfortType {
    public static final Long COMFORT_TYPE_CHEAP = 1L;
    public static final Long COMFORT_TYPE_COMFORT = 2L;
    public static final Long COMFORT_TYPE_LUXURY = 3L;

    @Id
    @Column(name = "travel_comfort_type_id")
    private Long id;

    private String travelComfortTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTravelComfortTypeName() {
        return travelComfortTypeName;
    }

    public void setTravelComfortTypeName(String travelComfortTypeName) {
        this.travelComfortTypeName = travelComfortTypeName;
    }
}
