package com.summerdev.travel.services.api.aviasales;

import com.summerdev.travel.constants.api.Urls;
import com.summerdev.travel.requests.api.aviasales.AviaSalesRequest;
import com.summerdev.travel.responses.api.aviasales.AviaSalesMainResponse;
import com.summerdev.travel.services.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@Service
public class AviaSalesSeviceImpl implements AviaSalesService, ApiService<AviaSalesMainResponse> {
    private static final Logger log = LoggerFactory.getLogger(AviaSalesSeviceImpl.class);

    public AviaSalesSeviceImpl() {
    }

    @Override
    public AviaSalesMainResponse get(AviaSalesRequest request) {

        request.setArrivalStation("MOW");
        request.setDepartureStation("HKT");
//        depart_date (optional)	-	Day or month of departure (yyyy-mm-dd or yyyy-mm).
//        return_date (optional)	-	Day or month of return (yyyy-mm-dd or yyyy-mm).

        request.setDepartDate("2021-05-11");
        request.setReturnDate("2021-05-24");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(Urls.URL_AVIASALES_GET_CHEAP_TICKETS)
                .queryParam("origin", request.getArrivalStation())
                .queryParam("destination", request.getDepartureStation())
                .queryParam("depart_date", request.getDepartDate())
                .queryParam("return_date", request.getReturnDate())
                .queryParam("token", System.getenv("ENV_AVIASALES_TOKEN"))
                .encode(StandardCharsets.US_ASCII);
        log.info("builder: {}", builder.toUriString());

        try {

            //ResponseEntity responseEntity = getRequest(builder, AviaSalesMainResponse.class);
            ResponseEntity<AviaSalesMainResponse> responseEntity = getRequest(builder, AviaSalesMainResponse.class);

            log.info("Get request status is {}", responseEntity.getStatusCode());

            return responseEntity.getBody();
        } catch (RestClientException e) {
            log.error("Get request failed, error: {}", e.getMessage());
        }

        return null;
    }

//    private String convertToSimpleDateFormat(Date date)
//    {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        return formatter.format(date);
//    }
}

//{
//        "success": true,
//        "data": {
//        "HKT": {
//        "0": {
//        "price": 40669,
//        "airline": "SU",
//        "flight_number": 278,
//        "departure_at": "2021-11-12T04:50:00Z",
//        "return_at": "2021-12-08T15:25:00Z",
//        "expires_at": "2021-04-04T04:24:40Z"
//        },
//        "1": {
//        "price": 35752,
//        "airline": "SQ",
//        "flight_number": 361,
//        "departure_at": "2021-11-12T12:20:00Z",
//        "return_at": "2021-12-09T03:10:00Z",
//        "expires_at": "2021-04-04T04:24:40Z"
//        },
//        "2": {
//        "price": 34732,
//        "airline": "EY",
//        "flight_number": 68,
//        "departure_at": "2021-11-09T09:15:00Z",
//        "return_at": "2021-12-08T03:55:00Z",
//        "expires_at": "2021-04-04T03:52:25Z"
//        },
//        "3": {
//        "price": 56932,
//        "airline": "QR",
//        "flight_number": 234,
//        "departure_at": "2021-09-30T14:15:00Z",
//        "return_at": "2021-12-01T11:35:00Z",
//        "expires_at": "2021-04-05T22:20:48Z"
//        }
//        }
//        },
//        "error": "",
//        "currency": "rub"
//        }
