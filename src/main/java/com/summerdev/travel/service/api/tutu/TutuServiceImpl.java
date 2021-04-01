package com.summerdev.travel.service.api.tutu;

import com.summerdev.travel.constants.api.TutuUrls;
import com.summerdev.travel.requests.api.tutu.TutuRequest;
import com.summerdev.travel.responses.api.tutu.TutuTrainsResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class TutuServiceImpl implements TutuService {
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
        ResponseEntity<TutuTrainsResponse> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, entity, TutuTrainsResponse.class);

        return response.getBody();
    }
}
