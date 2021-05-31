package com.summerdev.travel.service.api.tutu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.summerdev.travel.constant.api.Urls;
import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TutuRoute;
import com.summerdev.travel.entity.TutuStation;
import com.summerdev.travel.repository.TutuRouteRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.service.HttpRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class TutuServiceImpl implements TutuService {

    private final Logger log = LoggerFactory.getLogger(TutuServiceImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final TutuStationRepository tutuStationRepository;
    private final TutuRouteRepository tutuRouteRepository;
    private final HttpRequestService httpRequestService;

    public TutuServiceImpl(TutuStationRepository tutuStationDirectoryRepository, TutuRouteRepository tutuRouteRepository,
                           HttpRequestService httpRequestService) {
        this.tutuStationRepository = tutuStationDirectoryRepository;
        this.tutuRouteRepository = tutuRouteRepository;
        this.httpRequestService = httpRequestService;
    }

    @Override
    public List<TutuTrainsResponse> getTrainsInfo(String departureCity) {
        List<TutuTrainsResponse> responses = new ArrayList<>();

        if (departureCity == null || departureCity.equals("")) {
            throw new NullPointerException("Departure city cannot be null");
        }
        List<TutuStation> stations = tutuStationRepository.findByStationNameStartsWith(departureCity);

        for (TutuStation station : stations) {
            List<TutuRoute> routes = tutuRouteRepository.findByDepartureStation(station);

            for (TutuRoute route : routes) {
                int departId = route.getDepartureStation().getStationId().intValue();
                int arrivalId = route.getArrivalStation().getStationId().intValue();
                try {
                    TutuTrainsResponse response = getTrainsResponse(departId, arrivalId);
                    if (response != null) {
                        responses.add(response);
                    }
                } catch (Exception e) {
                    log.error("Get train info failed", e);
                }
            }
        }

        return responses;
    }

    @Override
    public List<TutuTrainsResponse> getTrainsInfo(GeoName departureCity) {
        return getTrainsInfo(departureCity.getGeoNameRu());
    }

    @Override
    public TutuTrainsResponse getTrainsResponse(int departureStation, int arrivalStation) {
        String response = getUnparsedTrainsResponse(departureStation, arrivalStation);
        if (response != null) {
            try {
                return objectMapper.readValue(response, TutuTrainsResponse.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation) {
        return getTrainsResponse(departureStation.getStationId().intValue(), arrivalStation.getStationId().intValue());
    }

    @Override
    public String getUnparsedTrainsResponse(int departureStation, int arrivalStation) {
        URI uri = UriComponentsBuilder.fromHttpUrl(Urls.URL_TUTU_GET_TRAINS)
                .queryParam("term", departureStation)
                .queryParam("term2", arrivalStation)
                .build()
                .toUri();
        HttpResponse<String> response = httpRequestService.getResponseFromUri(uri);

        if (response.statusCode() == HttpStatus.OK.value() &&
                !response.body().equals("[]")) {
            return response.body();
        }

        return null;
    }

}
