package com.summerdev.travel.entity.hotel;

import com.summerdev.travel.entity.directory.ComfortType;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.11.2021
 * Time: 20:24
 */
@Getter
@Table(name = "hotel_prices")
@Entity
public class HotelPrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_price_id")
    private Long id;

    @Column(name = "hotel_info_id", insertable = false, updatable = false)
    private Long hotelInfoId;

    @ManyToOne
    @JoinColumn(name = "hotel_info_id")
    private HotelInfo hotelInfo;

    private Double cost;

    @ManyToOne
    @JoinColumn(name = "comfort_type_id")
    private ComfortType comfortType;
}
