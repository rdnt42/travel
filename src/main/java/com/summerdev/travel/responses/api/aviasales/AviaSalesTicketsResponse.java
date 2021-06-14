package com.summerdev.travel.responses.api.aviasales;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesTicketsResponse {

    @JsonProperty("0")
    private AviaSalesTicketResponse aviaSalesTicketResponse;

    @JsonProperty("1")
    private AviaSalesTicketResponse aviaSalesTicketResponse2;

    @JsonProperty("2")
    private AviaSalesTicketResponse aviaSalesTicketResponse3;

    @JsonProperty("3")
    private AviaSalesTicketResponse aviaSalesTicketResponse4;

    public AviaSalesTicketsResponse() {
    }

    public AviaSalesTicketsResponse(AviaSalesTicketResponse aviaSalesTicketResponse, AviaSalesTicketResponse aviaSalesTicketResponse2, AviaSalesTicketResponse aviaSalesTicketResponse3, AviaSalesTicketResponse aviaSalesTicketResponse4) {
        this.aviaSalesTicketResponse = aviaSalesTicketResponse;
        this.aviaSalesTicketResponse2 = aviaSalesTicketResponse2;
        this.aviaSalesTicketResponse3 = aviaSalesTicketResponse3;
        this.aviaSalesTicketResponse4 = aviaSalesTicketResponse4;
    }

    public AviaSalesTicketResponse getAviaSalesTicketResponse() {
        return aviaSalesTicketResponse;
    }

    public void setAviaSalesTicketResponse(AviaSalesTicketResponse aviaSalesTicketResponse) {
        this.aviaSalesTicketResponse = aviaSalesTicketResponse;
    }

    public AviaSalesTicketResponse getAviaSalesTicketResponse2() {
        return aviaSalesTicketResponse2;
    }

    public void setAviaSalesTicketResponse2(AviaSalesTicketResponse aviaSalesTicketResponse2) {
        this.aviaSalesTicketResponse2 = aviaSalesTicketResponse2;
    }

    public AviaSalesTicketResponse getAviaSalesTicketResponse3() {
        return aviaSalesTicketResponse3;
    }

    public void setAviaSalesTicketResponse3(AviaSalesTicketResponse aviaSalesTicketResponse3) {
        this.aviaSalesTicketResponse3 = aviaSalesTicketResponse3;
    }

    public AviaSalesTicketResponse getAviaSalesTicketResponse4() {
        return aviaSalesTicketResponse4;
    }

    public void setAviaSalesTicketResponse4(AviaSalesTicketResponse aviaSalesTicketResponse4) {
        this.aviaSalesTicketResponse4 = aviaSalesTicketResponse4;
    }
}
