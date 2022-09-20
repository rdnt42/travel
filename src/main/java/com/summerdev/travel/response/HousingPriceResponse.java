package com.summerdev.travel.response;

import lombok.Builder;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 20.09.2022
 * Time: 22:55
 */
@Getter
@Builder
public class HousingPriceResponse {
    private final String hotelName;
    private final Long stars;
    private final String cost;
}
