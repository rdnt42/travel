package com.summerdev.travel.response;

import lombok.Builder;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 18.09.2022
 * Time: 15:10
 */
@Getter
@Builder
public class TransportPriceResponse {
    private String cost;
    private String comfortTypeName;
    private String seatTypeName;
    private String travelTimeMinutes;
    private String arrivalCity;
    private String departureCity;
}
