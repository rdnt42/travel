package com.summerdev.travel.services.api;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

public interface ApiService<R> {
    default ResponseEntity<R> getResponse(UriComponentsBuilder builder, Class<R> c) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, c);
    }

    default ResponseEntity<List<R>> getResponse(UriComponentsBuilder builder,
                                                ParameterizedTypeReference<List<R>> ref) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        // TODO: 05/04/2021 Тема, объясни плиз, почему это get а не post в "HttpMethod.GET"
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, ref);
    }
}
