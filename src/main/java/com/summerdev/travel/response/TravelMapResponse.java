package com.summerdev.travel.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TravelMapResponse {
    private List<TravelMapItemResponse> travelMapItems = new ArrayList<>();

    public void addItem(TravelMapItemResponse travelMapItem) {
        travelMapItems.add(travelMapItem);
    }

}
