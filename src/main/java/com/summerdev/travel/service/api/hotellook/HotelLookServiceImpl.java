package com.summerdev.travel.service.api.hotellook;

import com.summerdev.travel.constant.api.Urls;
import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.tutu.TutuRoute;
import com.summerdev.travel.entity.tutu.TutuStation;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public HotelLookHotelsResponse getHotelsResponse(HotelLookRequest request) {
        String encodeLocation;
        try {
            encodeLocation = URLEncoder.encode(request.getLocation(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        URI uri = UriComponentsBuilder
                .fromHttpUrl(Urls.URL_HOTEL_LOOK_GET_HOTELS)
                .queryParam("location", encodeLocation)
                .queryParam("checkIn", request.getCheckInFormated())
                .queryParam("checkOut", request.getCheckOutFormated())
                .queryParam("adults", request.getAdults())
                .queryParam("limit", request.getLimit())
                .queryParam("currency", request.getCurrency())
                .build(true)
                .toUri();
        log.info("builder: {}", uri);

        try {

            ParameterizedTypeReference<List<HotelLookHotelResponse>> ref = new ParameterizedTypeReference<>() {
            };
            ResponseEntity<List<HotelLookHotelResponse>> responseEntity = getResponseByRest(uri, ref);

            log.info("Get request status is {}", responseEntity.getStatusCode());

            return new HotelLookHotelsResponse(responseEntity.getBody());
        } catch (RestClientException e) {
            log.error("Get request failed, location: {}, error: {}", request.getLocation(), e.getMessage());
        }

        return null;
    }

    private List<HotelLookHotelsResponse> getHotelsInfo(TravelMapRequest request, List<TutuStation> arrivalStations) {
        List<HotelLookHotelsResponse> responses = new ArrayList<>();

        for (TutuStation station : arrivalStations) {
            Date outDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(outDate);
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            HotelLookHotelsResponse response = getHotelsResponse(
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
    public HotelLookHotelsResponse getHotelsInfo(GeoName city, Date startDate, Date endDate) {
        return getHotelsResponse(new HotelLookRequest(city.getGeoNameRu(), startDate, endDate));
    }
}
