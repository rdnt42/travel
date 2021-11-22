package com.summerdev.travel.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelMapResponse {
    private List<TravelMapItemResponse> travelMapItems = new ArrayList<>();

    public void addItem(TravelMapItemResponse itemResponse) {
        travelMapItems.add(itemResponse);
    }

    public List<TravelMapItemResponse> getTravelMapItems() {
        return travelMapItems;
    }

    public void setTravelMapItems(List<TravelMapItemResponse> travelMapItems) {
        this.travelMapItems = travelMapItems;
    }
}
