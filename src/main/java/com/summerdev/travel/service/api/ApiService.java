package com.summerdev.travel.service.api;


import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public interface ApiService<R> {

    default ResponseEntity<R> getResponseByRest(URI uri, Class<R> c) {
        RestTemplate restTemplate = new RestTemplate();
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionRequestTimeout(5000)
                        .setConnectTimeout(5000)
                        .setSocketTimeout(20000)
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(10)
                .build();

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return restTemplate.exchange(uri, HttpMethod.GET, entity, c);
    }

    default ResponseEntity<List<R>> getResponseByRest(URI uri,
                                                      ParameterizedTypeReference<List<R>> ref) {
        RestTemplate restTemplate = new RestTemplate();

        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionRequestTimeout(5000)
                        .setConnectTimeout(5000)
                        .setSocketTimeout(20000)
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(10)
                .build();

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return restTemplate.exchange(uri, HttpMethod.GET, entity, ref);
    }

}
