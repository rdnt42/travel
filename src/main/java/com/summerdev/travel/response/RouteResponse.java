package com.summerdev.travel.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RouteResponse {
    private TransportPriceResponse transportPrice;
}
