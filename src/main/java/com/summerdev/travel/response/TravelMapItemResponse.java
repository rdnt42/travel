package com.summerdev.travel.response;

import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.entity.train.TrainPrice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TravelMapItemResponse {
    private List<TrainPrice> trainPrice;
    private List<HotelPrice> hotelPrice;

    public TravelMapItemResponse(List<TrainPrice> trainPrice, List<HotelPrice> hotelPrice) {
        this.trainPrice = trainPrice;
        this.hotelPrice = hotelPrice;
    }
}
