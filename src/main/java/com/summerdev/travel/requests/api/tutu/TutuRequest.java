package com.summerdev.travel.requests.api.tutu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TutuRequest {
    @JsonProperty("term")
    private int departureStation;

    @JsonProperty("term2")
    private int arrivalStation;

    private String callback;

    public int getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(int departureStation) {
        this.departureStation = departureStation;
    }

    public int getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(int arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
