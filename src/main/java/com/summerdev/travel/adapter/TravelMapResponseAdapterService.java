package com.summerdev.travel.adapter;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.entity.train.TrainPrice;
import com.summerdev.travel.response.TravelMapItemResponse;
import com.summerdev.travel.response.TravelMapResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 02.03.2022
 * Time: 20:23
 */
@Service
public class TravelMapResponseAdapterService {

    public TravelMapResponse getResponseFromPrices(List<TrainPrice> pricesPerRoute, List<HotelPrice> pricesPerCity, List<GeoNameData> arrivalCities) {
        TravelMapResponse results = new TravelMapResponse();

        for (GeoNameData arrivalCity : arrivalCities) {
            List<TrainPrice> filteredTrainPrices = getFilteredTrainPricesByCity(pricesPerRoute, arrivalCity);
            List<HotelPrice> filteredHotelPrices = getFilteredHotelPricesByCity(pricesPerCity, arrivalCity);

            if (!pricesPerRoute.isEmpty() && !pricesPerCity.isEmpty()) {
                results.addItem(new TravelMapItemResponse(filteredTrainPrices, filteredHotelPrices));
            }
        }

        return results;
    }

    private List<HotelPrice> getFilteredHotelPricesByCity(List<HotelPrice> hotelPrices, GeoNameData city) {
        return hotelPrices.stream()
                .filter(hotelPrice -> hotelPrice.getHotelInfo().getCity().getId().equals(city.getId()))
                .limit(1)
                .collect(Collectors.toList());
    }

    private List<TrainPrice> getFilteredTrainPricesByCity(List<TrainPrice> trainPrices, GeoNameData city) {
        return trainPrices.stream()
                .filter(trainPrice -> trainPrice.getTrainInfo().getArrivalCity().getId().equals(city.getId()))
                .limit(1)
                .collect(Collectors.toList());
    }

}
