package com.summerdev.travel.response;

import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.entity.train.TrainPrice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class RouteResponse {
    private TrainPrice transportPrice;
    private HotelPrice housingPrice;

    private List<TrainPrice> trainPrices;
    private List<HotelPrice> hotelPrices;
}
