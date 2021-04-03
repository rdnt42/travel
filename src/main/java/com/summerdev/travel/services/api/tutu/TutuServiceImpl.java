package com.summerdev.travel.services.api.tutu;

import com.summerdev.travel.constants.api.Urls;
import com.summerdev.travel.entities.TutuRoute;
import com.summerdev.travel.entities.TutuStation;
import com.summerdev.travel.repositories.TutuRouteRepository;
import com.summerdev.travel.repositories.TutuStationRepository;
import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.requests.api.tutu.TutuRequest;
import com.summerdev.travel.responses.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.services.api.ApiService;
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
    public List<TutuTrainsResponse> getTrainsInfo(TravelMapRequest request) {
        List<TutuTrainsResponse> responses = new ArrayList<>();

        if (request.getDepartureCity() == null) {
            throw new NullPointerException("Departure city cannot be null");
        }
        List<TutuStation> stations = tutuStationRepository.findByStationName(request.getDepartureCity());

        for (TutuStation station : stations) {
            List<TutuRoute> routes = tutuRouteRepository.findByDepartureStation(station);

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
            ResponseEntity<TutuTrainsResponse> response = get(builder, TutuTrainsResponse.class);
            log.info("Get request status is {}", response.getStatusCode());

            return response.getBody();
        } catch (RestClientException e) {
            log.error("Get request failed, depart id: {}, arrival id: {}, error: {}",
                    request.getDepartureStation(), request.getArrivalStation(), e.getMessage());
        }

        return null;
    }
}
