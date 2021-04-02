package com.summerdev.travel.responses.api.tutu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TutuTrainsResponse {
    List<TutuTripItemResponse> trips = new ArrayList<>();

    public List<TutuTripItemResponse> getTrips() {
        return trips;
    }

    public void setTrips(List<TutuTripItemResponse> trips) {
        this.trips = trips;
    }
}
