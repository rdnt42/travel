package com.summerdev.travel.service.api.hotellook;

import com.summerdev.travel.constant.api.Urls;
import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TutuRoute;
import com.summerdev.travel.entity.TutuStation;
import com.summerdev.travel.repository.TutuRouteRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import com.summerdev.travel.request.TravelMapRequest;
import com.summerdev.travel.request.api.hotellook.HotelLookRequest;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelsResponse;
import com.summerdev.travel.service.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class HotelLookServiceImpl implements HotelLookService, ApiService<HotelLookHotelResponse> {
    private static final Logger log = LoggerFactory.getLogger(HotelLookServiceImpl.class);

    private final TutuStationRepository tutuStationRepository;
    private final TutuRouteRepository tutuRouteRepository;

    public HotelLookServiceImpl(TutuStationRepository tutuStationRepository, TutuRouteRepository tutuRouteRepository) {
        this.tutuStationRepository = tutuStationRepository;
        this.tutuRouteRepository = tutuRouteRepository;
    }

    @Override
    public HotelLookHotelsResponse get(HotelLookRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(Urls.URL_HOTEL_LOOK_GET_HOTELS)
                .queryParam("location", request.getLocation())
                .queryParam("checkIn", request.getCheckInFormated())
                .queryParam("checkOut", request.getCheckOutFormated())
                .queryParam("adults", request.getAdults())
                .queryParam("limit", request.getLimit())
                .queryParam("currency", request.getCurrency())
                .encode(StandardCharsets.US_ASCII);
        log.info("builder: {}", builder.toUriString());

        try {

            ParameterizedTypeReference ref = new ParameterizedTypeReference<List<HotelLookHotelResponse>>() {};
            ResponseEntity<List<HotelLookHotelResponse>> responseEntity = getResponseByRest(builder, ref);

            log.info("Get request status is {}", responseEntity.getStatusCode());

            return new HotelLookHotelsResponse(responseEntity.getBody());
        } catch (RestClientException e) {
            log.error("Get request failed, location: {}, error: {}", request.getLocation(), e.getMessage());
        }

        return null;
    }

    @Override
    public List<HotelLookHotelsResponse> getHotelsInfo(TravelMapRequest request, List<TutuStation> arrivalStations) {
        List<HotelLookHotelsResponse> responses = new ArrayList<>();

        for (TutuStation station : arrivalStations) {
            Date outDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(outDate);
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            HotelLookHotelsResponse response = get(
                    new HotelLookRequest(station.getStationName(), new Date(), calendar.getTime()));

            if (response != null) {
                responses.add(response);
            }
        }

        return responses;
    }

    @Override
    public List<HotelLookHotelsResponse> getHotelsInfo(TravelMapRequest request) {
        List<TutuStation> arrivalStations = new ArrayList<>();

        List<TutuStation> stations = tutuStationRepository.findByStationNameStartsWith(request.getDepartureCity());
        for (TutuStation station : stations) {
            List<TutuRoute> routes = tutuRouteRepository.findByDepartureStation(station);

            for (TutuRoute route : routes) {
                arrivalStations.add(route.getArrivalStation());
            }
        }

        return getHotelsInfo(request, arrivalStations);
    }

    @Override
    public HotelLookHotelsResponse getHotelsInfo(GeoName city) {
        Date outDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(outDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        return get(new HotelLookRequest(city.getGeoNameRu(), new Date(), calendar.getTime()));
    }
}
