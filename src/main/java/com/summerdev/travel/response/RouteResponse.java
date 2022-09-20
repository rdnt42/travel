package com.summerdev.travel.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RouteResponse {
    private final String arrivalCity;
    private final String departureCity;
    private final TransportPriceResponse transportPrice;
    private final HousingPriceResponse housingPrice;
    private final List<TransportPriceResponse> alternativeTransportPrice;
    private final List<HousingPriceResponse> alternativeHousingPrice;
}
