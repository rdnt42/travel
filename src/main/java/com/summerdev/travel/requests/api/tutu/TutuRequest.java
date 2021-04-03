package com.summerdev.travel.requests.api.tutu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TutuRequest {
    // https://support.travelpayouts.com/hc/ru/articles/360020147791-API-%D0%BE%D1%82-Tutu-ru
    @JsonProperty("term")
    private int departureStation;

    @JsonProperty("term2")
    private int arrivalStation;

    public TutuRequest() {
    }

    public TutuRequest(int departureStation, int arrivalStation) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

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

}
