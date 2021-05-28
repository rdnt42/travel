package com.summerdev.travel.response.api.hotellook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelLookPricesResponse {
    @JsonProperty("3")
    private Double smallPrice;

    @JsonProperty("50")
    private Double mediumPrice;

    @JsonProperty("99")
    private Double highPrice;

    public Double getSmallPrice() {
        return smallPrice;
    }

    public void setSmallPrice(Double smallPrice) {
        this.smallPrice = smallPrice;
    }

    public Double getMediumPrice() {
        return mediumPrice;
    }

    public void setMediumPrice(Double mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }
}
