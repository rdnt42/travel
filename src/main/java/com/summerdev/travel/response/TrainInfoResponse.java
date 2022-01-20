package com.summerdev.travel.response;

import com.summerdev.travel.entity.GeoNameData;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 14:45
 */
public class TrainInfoResponse {
    private GeoNameData departureCity;
    private GeoNameData arrivalCity;
    private Long travelTime;
    private String categoryName;
    private Long cost;

    public GeoNameData getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(GeoNameData departureCity) {
        this.departureCity = departureCity;
    }

    public GeoNameData getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(GeoNameData arrivalCity) {
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
