package com.summerdev.travel.responses;

import java.text.DateFormat;
import java.util.Objects;

public class TravelMapItem {
    String nameCity;
    String price;
    DateFormat dateFormat;

    public TravelMapItem() {
    }

    public TravelMapItem(String nameCity, String price, DateFormat dateFormat) {
        this.nameCity = nameCity;
        this.price = price;
        this.dateFormat = dateFormat;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelMapItem)) return false;
        TravelMapItem that = (TravelMapItem) o;
        return Objects.equals(getNameCity(), that.getNameCity()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getDateFormat(), that.getDateFormat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameCity(), getPrice(), getDateFormat());
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
