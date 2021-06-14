package com.summerdev.travel.responses.api.aviasales;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesDataResponse {

    @JsonProperty("HKT")
    private AviaSalesTicketsResponse aviaSalesTicketsResponse;

    public AviaSalesDataResponse() {
    }

    public AviaSalesDataResponse(AviaSalesTicketsResponse aviaSalesTicketsResponse) {
        this.aviaSalesTicketsResponse = aviaSalesTicketsResponse;
    }

    public AviaSalesTicketsResponse getAviaSalesTicketsResponse() {
        return aviaSalesTicketsResponse;
    }

    public void setAviaSalesTicketsResponse(AviaSalesTicketsResponse aviaSalesTicketsResponse) {
        this.aviaSalesTicketsResponse = aviaSalesTicketsResponse;
    }
}
