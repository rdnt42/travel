package com.summerdev.travel.response;

import com.summerdev.travel.entity.GeoName;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 14:45
 */
public class TrainInfoResponse {
    private GeoName departureCity;
    private GeoName arrivalCity;
    private Long travelTime;
    private String categoryName;
    private Long cost;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
