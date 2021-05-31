package com.summerdev.travel.response.api.tutu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TutuTripItemResponse {
    private String name;

    @JsonDeserialize(as = Long.class)
    private Long departureStation;

    @JsonDeserialize(as = Long.class)
    private Long arrivalStation;

    private String runDepartureStation;
    private String runArrivalStation;
    private String departureTime;
    private String arrivalTime;
    private String trainNumber;

    @JsonDeserialize(as = Long.class)
    private Long travelTimeInSeconds;
    private List<TutuRailwayCarriageResponse> categories = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Long departureStation) {
        this.departureStation = departureStation;
    }

    public Long getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Long arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getRunDepartureStation() {
        return runDepartureStation;
    }

    public void setRunDepartureStation(String runDepartureStation) {
        this.runDepartureStation = runDepartureStation;
    }

    public String getRunArrivalStation() {
        return runArrivalStation;
    }

    public void setRunArrivalStation(String runArrivalStation) {
        this.runArrivalStation = runArrivalStation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Long getTravelTimeInSeconds() {
        return travelTimeInSeconds;
    }

    public void setTravelTimeInSeconds(Long travelTimeInSeconds) {
        this.travelTimeInSeconds = travelTimeInSeconds;
    }

    public List<TutuRailwayCarriageResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<TutuRailwayCarriageResponse> categories) {
        this.categories = categories;
    }
}
