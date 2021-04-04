package com.summerdev.travel.responses.api.hotellook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class HotelLookHotelsResponse {
    List<HotelLookHotelResponse> hotels = new ArrayList<>();

    public HotelLookHotelsResponse() {
    }

    public HotelLookHotelsResponse(List<HotelLookHotelResponse> hotels) {
        this.hotels = hotels;
    }

    public List<HotelLookHotelResponse> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelLookHotelResponse> hotels) {
        this.hotels = hotels;
    }
}
