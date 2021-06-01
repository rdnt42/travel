package com.summerdev.travel.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 31.05.2021
 * Time: 23:42
 */
@Entity(name = "hotels_info")
public class HotelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotels_info_id")
    private Long id;



}
