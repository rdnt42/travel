package com.summerdev.travel.service;

import com.summerdev.travel.adapter.TravelMapResponseAdapterService;
import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.enums.ComfortTypes;
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
import java.util.Optional;

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

        Optional<GeoNameData> departureCity = geoNameRepository.findDistinctFirstByGeoNameRu(departureCityName);
        if (departureCity.isEmpty()) return new TravelMapResponse();

        ComfortTypes comfortTypes = ComfortTypes.fromString(travelComfortType);

        List<TrainPrice> trainPrices = trainPriceService.getTrainPricesForTrip(maxCost, comfortTypes, departureCity.get());
        List<GeoNameData> arrivalCities = trainPriceService.getArrivalCities(trainPrices);
        List<HotelPrice> hotelPrices = hotelPriceService.getHotelsPricesForTrip(arrivalCities, maxCost, comfortTypes);

        return travelMapResponseAdapterService.getResponseFromPrices(trainPrices, hotelPrices, arrivalCities);
    }
}
