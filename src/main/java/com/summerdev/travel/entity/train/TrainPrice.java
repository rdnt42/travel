package com.summerdev.travel.entity.train;

import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.directory.SeatType;
import lombok.Getter;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "comfort_type_id")
    private ComfortType comfortType;

    @Column(name = "seat_type_id")
    private Long seatTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_type_id", insertable = false, updatable = false)
    private SeatType seatType;

}
