package com.summerdev.travel.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 29.05.2021
 * Time: 16:28
 */
@Service
public class HttpRequestServiceImpl implements HttpRequestService{

    @Override
    public HttpResponse<String> getResponseFromUri(URI uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestApi = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        try {
            return client.send(requestApi, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

}
