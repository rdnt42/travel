package com.summerdev.travel.service;

import com.summerdev.travel.adapter.TravelMapResponseAdapterService;
import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.entity.train.TrainPrice;
import com.summerdev.travel.repository.GeoNameRepository;
import com.summerdev.travel.response.TravelMapResponse;
import com.summerdev.travel.service.route.HotelPriceService;
import com.summerdev.travel.service.route.TrainPriceService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TravelMapServiceImpl implements TravelMapService {

    @NonNull GeoNameRepository geoNameRepository;
    @NonNull TrainPriceService trainPriceService;
    @NonNull HotelPriceService hotelPriceService;
    @NonNull TravelMapResponseAdapterService travelMapResponseAdapterService;

    @Override
    public TravelMapResponse getTravelMap(String departureCityName, Long maxCost,
                                          String travelComfortType, Long days) {

        GeoNameData departureCity = geoNameRepository.findDistinctFirstByGeoNameRu(departureCityName)
                .orElse(null);
        if (departureCity == null) return new TravelMapResponse();

        ComfortType comfortType = ComfortType.fromString(travelComfortType);

        List<TrainPrice> trainPrices = trainPriceService.getTrainPricesForTrip(maxCost, comfortType, departureCity);
        List<GeoNameData> arrivalCities = trainPriceService.getArrivalCities(trainPrices);
        List<HotelPrice> hotelPrices = hotelPriceService.getHotelsPricesForTrip(arrivalCities, maxCost, comfortType);

        return travelMapResponseAdapterService.getResponseFromPrices(trainPrices, hotelPrices, arrivalCities);
    }

}
