package com.summerdev.travel.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TravelMapResponse {
    private List<RouteResponse> routeResponses = new ArrayList<>();

    public void addItem(RouteResponse travelMapItem) {
        routeResponses.add(travelMapItem);
    }

}
