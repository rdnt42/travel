package com.summerdev.travel.service;

import java.net.URI;
import java.net.http.HttpResponse;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 29.05.2021
 * Time: 16:29
 */
public interface HttpRequestService {
    HttpResponse<String> getResponseFromUri(URI uri);
}
