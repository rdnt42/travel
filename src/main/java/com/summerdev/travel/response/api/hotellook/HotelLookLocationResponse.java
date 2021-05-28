package com.summerdev.travel.response.api.hotellook;

public class HotelLookLocationResponse {
    private String country;
    private HotelLookCoordinateResponse geo;
    private String state;
    private String name;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public HotelLookCoordinateResponse getGeo() {
        return geo;
    }

    public void setGeo(HotelLookCoordinateResponse geo) {
        this.geo = geo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
