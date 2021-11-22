package com.summerdev.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 19:24
 */
@Entity
@Table(name = "seat_types")
public class SeatType implements Serializable {
    public static final Long SEAT_TYPE_ID_COMMON = 0L;
    public static final Long SEAT_TYPE_ID_ECONOMY = 1L;
    public static final Long SEAT_TYPE_ID_COUPE = 2L;
    public static final Long SEAT_TYPE_ID_SEDENTARY = 3L;
    public static final Long SEAT_TYPE_ID_LUX = 4L;
    public static final Long SEAT_TYPE_ID_SOFT = 5L;

    @Id
    @Column(name = "seat_type_id")
    private Long id;

    private String seatTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatTypeName() {
        return seatTypeName;
    }

    public void setSeatTypeName(String seatTypeName) {
        this.seatTypeName = seatTypeName;
    }
}
