package com.summerdev.travel.requests.api.aviasales;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class AviaSalesRequest {

    @JsonProperty("origin")
    private int departureStation;

    @JsonProperty("destination")
    private int arrivalStation;

    @JsonProperty("depart_date")
    private Date depart_date;

    @JsonProperty("return_date")
    private Date return_date;

    public AviaSalesRequest() {
    }

    public AviaSalesRequest(int departureStation, int arrivalStation, Date depart_date) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.depart_date = depart_date;
    }

    public AviaSalesRequest(int departureStation, int arrivalStation, Date depart_date, Date return_date) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.depart_date = depart_date;
        this.return_date = return_date;
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

    public Date getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(Date depart_date) {
        this.depart_date = depart_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AviaSalesRequest)) return false;
        AviaSalesRequest that = (AviaSalesRequest) o;
        return departureStation == that.departureStation && arrivalStation == that.arrivalStation && Objects.equals(depart_date, that.depart_date) && Objects.equals(return_date, that.return_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureStation, arrivalStation, depart_date, return_date);
    }
}
