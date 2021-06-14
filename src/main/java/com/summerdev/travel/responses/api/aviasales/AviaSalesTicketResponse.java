package com.summerdev.travel.responses.api.aviasales;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesTicketResponse {

    private double price;
    private String airline;
    private String flight_number;
    private Date departure_at;
    private Date return_at;
    private Date expires_at;

    public AviaSalesTicketResponse() {
    }

    public AviaSalesTicketResponse(double price, String airline, String flight_number, Date departure_at, Date return_at, Date expires_at) {
        this.price = price;
        this.airline = airline;
        this.flight_number = flight_number;
        this.departure_at = departure_at;
        this.return_at = return_at;
        this.expires_at = expires_at;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public Date getDeparture_at() {
        return departure_at;
    }

    public void setDeparture_at(Date departure_at) {
        this.departure_at = departure_at;
    }

    public Date getReturn_at() {
        return return_at;
    }

    public void setReturn_at(Date return_at) {
        this.return_at = return_at;
    }

    public Date getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(Date expires_at) {
        this.expires_at = expires_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AviaSalesTicketResponse)) return false;
        AviaSalesTicketResponse that = (AviaSalesTicketResponse) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(airline, that.airline) && Objects.equals(flight_number, that.flight_number) && Objects.equals(departure_at, that.departure_at) && Objects.equals(return_at, that.return_at) && Objects.equals(expires_at, that.expires_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, airline, flight_number, departure_at, return_at, expires_at);
    }

    @Override
    public String toString() {
        return "AviaSalesTicketResponse{" +
                "price=" + price +
                ", airlane='" + airline + '\'' +
                ", flight_number='" + flight_number + '\'' +
                ", departure_at=" + departure_at +
                ", return_at=" + return_at +
                ", expires_at=" + expires_at +
                '}';
    }
//  "price": 40669,
//  "airline": "SU",
//  "flight_number": 278,
//  "departure_at": "2021-11-12T04:50:00Z",
//  "return_at": "2021-12-08T15:25:00Z",
//  "expires_at": "2021-04-04T04:24:40Z"
}
