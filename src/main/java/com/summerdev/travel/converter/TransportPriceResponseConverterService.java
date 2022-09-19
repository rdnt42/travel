package com.summerdev.travel.converter;

import com.summerdev.travel.entity.train.TrainPrice;
import com.summerdev.travel.response.TransportPriceResponse;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 18.09.2022
 * Time: 15:23
 */
@Service
public class TransportPriceResponseConverterService {
    public TransportPriceResponse convert(TrainPrice price) {
        String travelTime = convertTravelTime(price.getTrainInfo().getTravelTime());
        String cost = convertCost(price.getCost());

        return TransportPriceResponse
                .builder()
                .cost(cost)
                .arrivalCity(price.getTrainInfo().getArrivalCity().getGeoNameRu())
                .departureCity(price.getTrainInfo().getDepartureCity().getGeoNameRu())
                .comfortTypeName(price.getComfortType().getComfortTypeName())
                .seatTypeName(price.getSeatType().getSeatTypeName())
                .travelTime(travelTime)
                .build();
    }

    private String convertTravelTime(long seconds) {
        String hours = String.valueOf(seconds / 60 / 60);
        String minutes = String.valueOf(seconds / 60 % 60);

        return String.format("%s ч %s м", hours, minutes);
    }

    private String convertCost(double cost) {
        return String.format("%.2f", cost);
    }
}
