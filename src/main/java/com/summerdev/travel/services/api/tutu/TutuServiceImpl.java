package com.summerdev.travel.services.api.tutu;

import com.summerdev.travel.constants.api.TutuUrls;
import com.summerdev.travel.entities.TutuRoute;
import com.summerdev.travel.entities.TutuStation;
import com.summerdev.travel.repositories.TutuRouteRepository;
import com.summerdev.travel.repositories.TutuStationRepository;
import com.summerdev.travel.requests.TravelMapRequest;
import com.summerdev.travel.requests.api.tutu.TutuRequest;
import com.summerdev.travel.responses.api.tutu.TutuTrainsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TutuServiceImpl implements TutuService {

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
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TutuUrls.URL_TUTU_GET_TRAINS)
                .queryParam("term", request.getDepartureStation())
                .queryParam("term2", request.getArrivalStation());
        try {
            ResponseEntity<TutuTrainsResponse> response = restTemplate.exchange(
                    builder.toUriString(), HttpMethod.GET, entity, TutuTrainsResponse.class);
            log.info("API: tutu; method: get; mess: request status is {}", response.getStatusCode());

            return response.getBody();
        } catch (RestClientException e) {
            log.error("API: tutu; method: get; mess: request failed, depart id: {}, arrival id: {}",
                    request.getDepartureStation(), request.getArrivalStation(), e.getMessage());
            return null;
        }
    }
}
