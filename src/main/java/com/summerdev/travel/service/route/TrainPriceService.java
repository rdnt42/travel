package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortTypes;
import com.summerdev.travel.entity.train.TrainPrice;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 02.03.2022
 * Time: 19:30
 */
public interface TrainPriceService {
    List<TrainPrice> getTrainPricesForTrip(Long totalBudget, ComfortTypes comfortType, GeoNameData departureCity);

    List<GeoNameData> getArrivalCities(List<TrainPrice> trainPrices);
}
