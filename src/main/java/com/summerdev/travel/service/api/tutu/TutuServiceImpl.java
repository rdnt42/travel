package com.summerdev.travel.service.api.tutu;

import com.summerdev.travel.constant.api.Urls;
import com.summerdev.travel.entity.TutuRoute;
import com.summerdev.travel.entity.TutuStation;
import com.summerdev.travel.repository.TutuRouteRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import com.summerdev.travel.request.api.tutu.TutuRequest;
import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.service.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutuServiceImpl implements TutuService, ApiService<TutuTrainsResponse> {

    private static final Logger log = LoggerFactory.getLogger(TutuServiceImpl.class);

    private final TutuStationRepository tutuStationRepository;
    private final TutuRouteRepository tutuRouteRepository;

    public TutuServiceImpl(TutuStationRepository tutuStationDirectoryRepository, TutuRouteRepository tutuRouteRepository) {
        this.tutuStationRepository = tutuStationDirectoryRepository;
        this.tutuRouteRepository = tutuRouteRepository;
    }

    @Override
    public List<TutuTrainsResponse> getTrainsInfo(String departureCity) {
        List<TutuTrainsResponse> responses = new ArrayList<>();

        if (departureCity == null || departureCity.equals("")) {
            throw new NullPointerException("Departure city cannot be null");
        }
        List<TutuStation> stations = tutuStationRepository.findByStationNameStartsWith(departureCity);

        for (TutuStation station : stations) {
            List<TutuRoute> routes = tutuRouteRepository.findByDepartureStationAndPopularityGreaterThanEqual(station, 10L);

            for (TutuRoute route : routes) {
                int departId = route.getDepartureStation().getStationId().intValue();
                int arrivalId = route.getArrivalStation().getStationId().intValue();
                try {
                    TutuTrainsResponse response = get(new TutuRequest(departId, arrivalId));
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
    public TutuTrainsResponse get(TutuRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Urls.URL_TUTU_GET_TRAINS)
                .queryParam("term", request.getDepartureStation())
                .queryParam("term2", request.getArrivalStation());

        try {
            ResponseEntity<TutuTrainsResponse> response = getResponse(builder, TutuTrainsResponse.class);
            log.info("Get request status is {}", response.getStatusCode());

            return response.getBody();
        } catch (RestClientException e) {
            log.error("Get request failed, depart id: {}, arrival id: {}, error: {}",
                    request.getDepartureStation(), request.getArrivalStation(), e.getMessage());
        }

        return null;
    }
}
