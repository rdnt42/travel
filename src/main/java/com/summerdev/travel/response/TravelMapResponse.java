package com.summerdev.travel.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelMapResponse {
    ArrayList<TravelMapItemResponse> travelMapItems = new ArrayList<>();

    public ArrayList<TravelMapItemResponse> getTravelMapItems() {
        return travelMapItems;
    }

    public void setTravelMapItems(ArrayList<TravelMapItemResponse> travelMapItems) {
        this.travelMapItems = travelMapItems;
    }
}
