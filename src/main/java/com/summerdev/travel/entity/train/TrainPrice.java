package com.summerdev.travel.entity.train;

import com.summerdev.travel.entity.directory.SeatType;
import com.summerdev.travel.enums.ComfortTypes;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 22:00
 */

@Getter
@Table(name = "train_prices")
@Entity
public class TrainPrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_price_id")
    private Long id;

    @Column(name = "train_info_id", insertable = false, updatable = false)
    private Long trainInfoId;

    @ManyToOne
    @JoinColumn(name = "train_info_id")
    private TrainInfo trainInfo;

    private Double cost;

    @Column(name = "comfort_type")
    @Enumerated(EnumType.STRING)
    private ComfortTypes comfortType;

    @Column(name = "seat_type_id")
    private Long seatTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_type_id", insertable = false, updatable = false)
    private SeatType seatType;

}
