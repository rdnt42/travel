package com.summerdev.travel.response.api.hotellook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelLookHotelResponse {
    private HotelLookLocationResponse location;
    private Double priceAvg;
    private HotelLookPricesResponse pricePercentile;
    private String hotelName;
    private Long stars;
    private Long hotelId;
    private Double priceFrom;

    public HotelLookLocationResponse getLocation() {
        return location;
    }

    public void setLocation(HotelLookLocationResponse location) {
        this.location = location;
    }

    public Double getPriceAvg() {
        return priceAvg;
    }

    public void setPriceAvg(Double priceAvg) {
        this.priceAvg = priceAvg;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public HotelLookPricesResponse getPricePercentile() {
        return pricePercentile;
    }

    public void setPricePercentile(HotelLookPricesResponse pricePercentile) {
        this.pricePercentile = pricePercentile;
    }
}
