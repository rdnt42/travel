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
    private final String cost;
    private final String seatTypeName;
    private final String travelTime;
}
