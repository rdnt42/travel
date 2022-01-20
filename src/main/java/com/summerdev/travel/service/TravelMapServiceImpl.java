package com.summerdev.travel.service;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.entity.train.TrainPrice;
import com.summerdev.travel.repository.GeoNameRepository;
import com.summerdev.travel.repository.HotelPriceRepository;
import com.summerdev.travel.repository.TrainPriceRepository;
import com.summerdev.travel.response.TravelMapItemResponse;
import com.summerdev.travel.response.TravelMapResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TravelMapServiceImpl implements TravelMapService {

    @NonNull GeoNameRepository geoNameRepository;
    @NonNull HotelPriceRepository hotelPriceRepository;
    @NonNull TrainPriceRepository trainPriceRepository;

    @Override
    public TravelMapResponse getTravelMap(String departureCityName, Long maxCost,
                                          String travelComfortType, Long days) {
        // TODO rework to different services
        Double testCost = maxCost / 3.0;

        GeoNameData departureCity = geoNameRepository.findDistinctFirstByGeoNameRu(departureCityName)
                .orElse(null);
        if (departureCity == null) return new TravelMapResponse();

        List<TrainPrice> trainPrices = trainPriceRepository.findAllByTrainInfoDepartureCityAndCostLessThanAndComfortType(
                departureCity, testCost, ComfortType.fromString(travelComfortType));

        List<GeoNameData> arrivalCities = trainPrices.stream()
                .map(price -> price.getTrainInfo().getArrivalCity())
                .distinct()
                .collect(Collectors.toList());

        List<HotelPrice> prices = hotelPriceRepository.findAllByHotelInfoCityInAndCostLessThanAndComfortType(
                arrivalCities, testCost / days, ComfortType.fromString(travelComfortType));


        TravelMapResponse results = new TravelMapResponse();
        for (GeoNameData arrivalCity : arrivalCities) {
            List<TrainPrice> pricesPerRoute = trainPrices.stream()
                    .filter(trainPrice -> trainPrice.getTrainInfo().getArrivalCity() == arrivalCity)
                    .limit(1)
                    .collect(Collectors.toList());

            List<HotelPrice> pricesPerCity = prices.stream()
                    .filter(hotelPrice -> hotelPrice.getHotelInfo().getCity() == arrivalCity)
                    .limit(1)
                    .collect(Collectors.toList());

            if (!pricesPerRoute.isEmpty() && !pricesPerCity.isEmpty()) {
                results.addItem(new TravelMapItemResponse(pricesPerRoute, pricesPerCity));
            }
        }

        return results;
    }

}
