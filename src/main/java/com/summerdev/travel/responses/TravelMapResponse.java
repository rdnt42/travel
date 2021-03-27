package com.summerdev.travel.responses;

import java.util.ArrayList;

public class TravelMapResponse {
    ArrayList<TravelMapItem> travelMapItems = new ArrayList<>();

    public ArrayList<TravelMapItem> getTravelMapItems() {
        return travelMapItems;
    }

    public void setTravelMapItems(ArrayList<TravelMapItem> travelMapItems) {
        this.travelMapItems = travelMapItems;
    }
}
