package com.summerdev.travel.responses.api.tutu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TutuTripItemResponse {
    private String name;
    private String departureStation;
    private String arrivalStation;
    private String runDepartureStation;
    private String runArrivalStation;
    private String departureTime;
    private String arrivalTime;
    private String trainNumber;
    private String travelTimeInSeconds;
    private List<TutuRailwayCarriage> categories = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTravelTimeInSeconds() {
        return travelTimeInSeconds;
    }

    public void setTravelTimeInSeconds(String travelTimeInSeconds) {
        this.travelTimeInSeconds = travelTimeInSeconds;
    }

    public List<TutuRailwayCarriage> getCategories() {
        return categories;
    }

    public void setCategories(List<TutuRailwayCarriage> categories) {
        this.categories = categories;
    }
}
