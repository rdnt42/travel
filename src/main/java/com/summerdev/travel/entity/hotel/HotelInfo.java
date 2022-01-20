package com.summerdev.travel.entity.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.summerdev.travel.entity.GeoNameData;
import lombok.Data;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 31.05.2021
 * Time: 23:42
 */

@Getter
@Entity(name = "hotels_info")
public class HotelInfo implements Serializable {
    @Id
    @Column(name = "hotel_info_id")
    private Long id;

    private String hotelName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private GeoNameData city;

    private Long stars;

    private Date lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "hotelInfo", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<HotelPrice> hotelPrices;
}
