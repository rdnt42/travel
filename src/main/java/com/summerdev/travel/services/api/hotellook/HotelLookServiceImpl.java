package com.summerdev.travel.services.api.hotellook;

import com.summerdev.travel.constants.api.Urls;
import com.summerdev.travel.requests.api.hotellook.HotelLookRequest;
import com.summerdev.travel.responses.api.hotellook.HotelLookHotelsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

public class HotelLookServiceImpl implements HotelLookService {
    private static final Logger log = LoggerFactory.getLogger(HotelLookServiceImpl.class);

    @Override
    public HotelLookHotelsResponse get(HotelLookRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Urls.URL_HOTEL_LOOK_GET_HOTELS)
                .queryParam("location", request.getLocation())
                .queryParam("checkIn", request.getCheckIn())
                .queryParam("checkOut", request.getGetCheckOut());
        try {
            ResponseEntity<HotelLookHotelsResponse> response = restTemplate.exchange(
                    builder.toUriString(), HttpMethod.GET, entity, HotelLookHotelsResponse.class);
            log.info("API: hotellook; method: get; mess: request status is {}", response.getStatusCode());

            return response.getBody();
        } catch (RestClientException e) {
            log.error("API: hotellook; method: get; mess: request failed, location: {}, error: {}",
                    request.getLocation(), e.getMessage());
            return null;
        }

    }
}
