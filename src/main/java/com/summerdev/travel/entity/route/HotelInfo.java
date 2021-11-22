package com.summerdev.travel.entity.route;

import com.summerdev.travel.entity.GeoName;

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
    @Column(name = "hotel_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private GeoName city;

    private Long stars;

    private Long cost;

    private Boolean isActualData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeoName getCity() {
        return city;
    }

    public void setCity(GeoName city) {
        this.city = city;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Boolean getActualData() {
        return isActualData;
    }

    public void setActualData(Boolean actualData) {
        isActualData = actualData;
    }
}
