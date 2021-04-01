package com.summerdev.travel.responses.api.tutu;

import java.util.ArrayList;
import java.util.List;

public class TutuTrainsResponse {
    List<TutuTripItemResponse> trips = new ArrayList<>();

    public List<TutuTripItemResponse> getTrips() {
        return trips;
    }

    public void setTrips(List<TutuTripItemResponse> trips) {
        this.trips = trips;
    }
}
