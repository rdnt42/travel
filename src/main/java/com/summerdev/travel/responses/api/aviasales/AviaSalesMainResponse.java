package com.summerdev.travel.responses.api.aviasales;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesMainResponse {

    private boolean result;
    private AviaSalesDataResponse aviaSalesDataResponse;
    private String errorFromResponse;
    private String currency;

    public AviaSalesMainResponse(boolean result, AviaSalesDataResponse aviaSalesDataResponse, String errorFromResponse, String currency) {
        this.result = result;
        this.aviaSalesDataResponse = aviaSalesDataResponse;
        this.errorFromResponse = errorFromResponse;
        this.currency = currency;
    }

    public AviaSalesMainResponse(Object body) {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public AviaSalesDataResponse getAviaSalesDataResponse() {
        return aviaSalesDataResponse;
    }

    public void setAviaSalesDataResponse(AviaSalesDataResponse aviaSalesDataResponse) {
        this.aviaSalesDataResponse = aviaSalesDataResponse;
    }

    public String getErrorFromResponse() {
        return errorFromResponse;
    }

    public void setErrorFromResponse(String errorFromResponse) {
        this.errorFromResponse = errorFromResponse;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AviaSalesMainResponse)) return false;
        AviaSalesMainResponse that = (AviaSalesMainResponse) o;
        return result == that.result && Objects.equals(aviaSalesDataResponse, that.aviaSalesDataResponse) && Objects.equals(errorFromResponse, that.errorFromResponse) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, aviaSalesDataResponse, errorFromResponse, currency);
    }

    @Override
    public String toString() {
        return "AviaSalesMainResponse{" +
                "result=" + result +
                ", aviaSalesDataResponse=" + aviaSalesDataResponse +
                ", errorFromResponse='" + errorFromResponse + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
