package com.summerdev.travel.responses.api.hotellook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelLookPricesResponse {
    @JsonProperty("3")
    private Double smallPrice;

    @JsonProperty("50")
    private Double mediumPrice;

    @JsonProperty("99")
    private Double highPrice;

}
