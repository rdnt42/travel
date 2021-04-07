package com.summerdev.travel.requests.api.aviasales;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class AviaSalesRequest {

    @JsonProperty("origin")
    private String departureStation;

    @JsonProperty("destination")
    private String arrivalStation;

    @JsonProperty("depart_date")
    private Date depart_date;

    @JsonProperty("return_date")
    private Date return_date;

    public AviaSalesRequest() {
    }

    public AviaSalesRequest(String departureStation, String arrivalStation, Date depart_date) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.depart_date = depart_date;
    }

    public AviaSalesRequest(String departureStation, String arrivalStation, Date depart_date, Date return_date) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.depart_date = depart_date;
        this.return_date = return_date;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
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
        return Objects.equals(departureStation, that.departureStation) && Objects.equals(arrivalStation, that.arrivalStation) && Objects.equals(depart_date, that.depart_date) && Objects.equals(return_date, that.return_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureStation, arrivalStation, depart_date, return_date);
    }

    @Override
    public String toString() {
        return "AviaSalesRequest{" +
                "departureStation='" + departureStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", depart_date=" + depart_date +
                ", return_date=" + return_date +
                '}';
    }
}
