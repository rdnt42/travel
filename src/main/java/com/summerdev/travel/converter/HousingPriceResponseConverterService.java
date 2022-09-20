package com.summerdev.travel.converter;

import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.response.HousingPriceResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 20.09.2022
 * Time: 23:54
 */
@Service
public class HousingPriceResponseConverterService {
    public HousingPriceResponse convert(HotelPrice price) {
        return HousingPriceResponse
                .builder()
                .cost(price.getCost().toString())
                .hotelName(price.getHotelInfo().getHotelName())
                .stars(price.getHotelInfo().getStars())
                .build();
    }

    public List<HousingPriceResponse> convert(List<HotelPrice> prices) {
        List<HousingPriceResponse> responses = new ArrayList<>();
        for (HotelPrice price : prices) {
            responses.add(convert(price));
        }

        return responses;
    }
}
