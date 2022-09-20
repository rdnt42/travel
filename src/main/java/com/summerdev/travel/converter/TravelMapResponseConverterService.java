package com.summerdev.travel.converter;

import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.entity.train.TrainPrice;
import com.summerdev.travel.response.HousingPriceResponse;
import com.summerdev.travel.response.RouteResponse;
import com.summerdev.travel.response.TransportPriceResponse;
import com.summerdev.travel.response.TravelMapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 02.03.2022
 * Time: 20:23
 */
@RequiredArgsConstructor
@Service
public class TravelMapResponseConverterService {
    private final TransportPriceResponseConverterService transportPriceResponseConverterService;
    private final HousingPriceResponseConverterService housingPriceResponseConverterService;

    public TravelMapResponse getResponseFromPrices(List<TrainPrice> trainPrices, List<HotelPrice> hotelPrices, String departureCity) {
        TravelMapResponse result = new TravelMapResponse();

        Map<String, List<TrainPrice>> trainPricesMap = trainPrices.stream()
                .collect(Collectors.groupingBy(t -> t.getTrainInfo().getArrivalCity().getGeoNameRu()));
        Map<String, List<HotelPrice>> hotelPricesMap = hotelPrices.stream()
                .collect(Collectors.groupingBy(t -> t.getHotelInfo().getCity().getGeoNameRu()));
        Set<String> arrivalCities = trainPricesMap.keySet();

        // TODO fix constant
        updateConstantLimits(hotelPricesMap);
        updateConstantLimits(trainPricesMap);

        for (String arrivalCity : arrivalCities) {
            List<TrainPrice> preparedTrainPrices = trainPricesMap.get(arrivalCity);
            List<HotelPrice> filteredHotelPrices = hotelPricesMap.get(arrivalCity);

            RouteResponse routeResponse = getRouteResponse(preparedTrainPrices, filteredHotelPrices, arrivalCity, departureCity);

            result.addItem(routeResponse);
        }

        return result;
    }

    private <T> void updateConstantLimits(Map<String, List<T>> map) {
        for (Map.Entry<String, List<T>> entry : map.entrySet()) {
            List<T> limitedList = entry.getValue().stream()
                    .limit(10)
                    .collect(Collectors.toList());
            entry.setValue(limitedList);
        }
    }

    private RouteResponse getRouteResponse(List<TrainPrice> trainPrices, List<HotelPrice> hotelPrices, String arrivalCity, String departureCity) {
        TrainPrice lowerTrainPrice = getLowerTrainPrice(trainPrices);
        trainPrices.remove(lowerTrainPrice);

        HotelPrice lowerHotelPrice = getLowerHotelPrice(hotelPrices);
        hotelPrices.remove(lowerHotelPrice);

        TransportPriceResponse transport = transportPriceResponseConverterService.convert(lowerTrainPrice);
        HousingPriceResponse hotel = housingPriceResponseConverterService.convert(lowerHotelPrice);

        List<TransportPriceResponse> alternativeTransport = transportPriceResponseConverterService.convert(trainPrices);
        List<HousingPriceResponse> alternativeHotels = housingPriceResponseConverterService.convert(hotelPrices);

        return RouteResponse.builder()
                .arrivalCity(arrivalCity)
                .departureCity(departureCity)
                .transportPrice(transport)
                .housingPrice(hotel)
                .alternativeTransportPrice(alternativeTransport)
                .alternativeHousingPrice(alternativeHotels)
                .build();
    }

    private HotelPrice getLowerHotelPrice(List<HotelPrice> hotelPrices) {
        return hotelPrices.stream()
                .min(Comparator.comparingDouble(HotelPrice::getCost))
                .orElse(null);
    }

    private TrainPrice getLowerTrainPrice(List<TrainPrice> trainPrices) {
        return trainPrices.stream()
                .min(Comparator.comparingDouble(TrainPrice::getCost))
                .orElse(null);
    }
}
