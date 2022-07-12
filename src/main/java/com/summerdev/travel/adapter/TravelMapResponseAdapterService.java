package com.summerdev.travel.adapter;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.entity.train.TrainPrice;
import com.summerdev.travel.response.RouteResponse;
import com.summerdev.travel.response.TravelMapResponse;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    // TODO fix this dirty hack
    private static final int ROUTE_LIMITS = 4;

    public TravelMapResponse getResponseFromPrices(List<TrainPrice> pricesPerRoute, List<HotelPrice> pricesPerCity, List<GeoNameData> arrivalCities) {
        TravelMapResponse result = new TravelMapResponse();

        for (GeoNameData arrivalCity : arrivalCities) {
            List<TrainPrice> preparedTrainPrices = getPreparedTrainPrices(pricesPerRoute, arrivalCity);
            List<HotelPrice> filteredHotelPrices = getPreparedHotelPrices(pricesPerCity, arrivalCity);

            RouteResponse routeResponse = getRouteResponse(preparedTrainPrices, filteredHotelPrices);

            result.addItem(routeResponse);
        }

        return getFilteredResponse(result);
    }

    private RouteResponse getRouteResponse(List<TrainPrice> trainPrices, List<HotelPrice> hotelPrices) {
        TrainPrice lowerTrainPrice = getLowerTrainPrice(trainPrices);
        trainPrices.remove(lowerTrainPrice);

        HotelPrice lowerHotelPrice = getLowerHotelPrice(hotelPrices);
        hotelPrices.remove(lowerHotelPrice);

        return RouteResponse.builder()
                .hotelPrices(hotelPrices)
                .housingPrice(lowerHotelPrice)
                .trainPrices(trainPrices)
                .transportPrice(lowerTrainPrice)
                .build();
    }

    private TravelMapResponse getFilteredResponse(TravelMapResponse travelMapResponse) {
        List<RouteResponse> filteredResponses = travelMapResponse.getRouteResponses().stream()
                .limit(ROUTE_LIMITS)
                .toList();
        travelMapResponse.setRouteResponses(filteredResponses);

        return travelMapResponse;
    }

    private List<HotelPrice> getPreparedHotelPrices(List<HotelPrice> hotelPrices, GeoNameData city) {
        return hotelPrices.stream()
                .filter(hotelPrice -> hotelPrice.getHotelInfo().getCity().getId().equals(city.getId()))
                .sorted(Comparator.comparingDouble(HotelPrice::getCost))
                .limit(ROUTE_LIMITS)
                .collect(Collectors.toList());
    }

    private HotelPrice getLowerHotelPrice(List<HotelPrice> hotelPrices) {
        return hotelPrices.stream()
                .min(Comparator.comparingDouble(HotelPrice::getCost))
                .orElse(null);
    }

    private List<TrainPrice> getPreparedTrainPrices(List<TrainPrice> trainPrices, GeoNameData city) {
        return trainPrices.stream()
                .filter(trainPrice -> trainPrice.getTrainInfo().getArrivalCity().getId().equals(city.getId()))
                .sorted(Comparator.comparingDouble(TrainPrice::getCost))
                .limit(ROUTE_LIMITS)
                .collect(Collectors.toList());
    }

    private TrainPrice getLowerTrainPrice(List<TrainPrice> trainPrices) {
        return trainPrices.stream()
                .min(Comparator.comparingDouble(TrainPrice::getCost))
                .orElse(null);
    }
}
