package com.summerdev.travel.service;

import com.summerdev.travel.adapter.TravelMapResponseAdapterService;
import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.entity.train.TrainPrice;
import com.summerdev.travel.repository.ComfortTypeRepository;
import com.summerdev.travel.repository.GeoNameRepository;
import com.summerdev.travel.response.TravelMapResponse;
import com.summerdev.travel.service.route.HotelPriceService;
import com.summerdev.travel.service.route.TrainPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TravelMapServiceImpl implements TravelMapService {

    private final GeoNameRepository geoNameRepository;
    private final TrainPriceService trainPriceService;
    private final HotelPriceService hotelPriceService;
    private final TravelMapResponseAdapterService travelMapResponseAdapterService;
    private final ComfortTypeRepository comfortTypeRepository;

    @Override
    public TravelMapResponse getTravelMap(String departureCityName, Long maxCost,
                                          Integer comfortTypeId, Long days) {

        Optional<GeoNameData> departureCity = geoNameRepository.findDistinctFirstByGeoNameRu(departureCityName);
        if (departureCity.isEmpty()) return new TravelMapResponse();

        ComfortType comfortType = comfortTypeRepository.findById(comfortTypeId)
                .orElseThrow(() -> new NullPointerException("Comfort type with id: " + comfortTypeId + "doesn't exist"));

        List<TrainPrice> trainPrices = trainPriceService.getTrainPricesForTrip(maxCost, comfortType, departureCity.get());
        List<GeoNameData> arrivalCities = trainPriceService.getArrivalCities(trainPrices);
        List<HotelPrice> hotelPrices = hotelPriceService.getHotelsPricesForTrip(arrivalCities, maxCost, comfortType);

        return travelMapResponseAdapterService.getResponseFromPrices(trainPrices, hotelPrices, arrivalCities);
    }
}
