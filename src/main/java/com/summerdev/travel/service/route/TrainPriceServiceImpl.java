package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.train.TrainPrice;
import com.summerdev.travel.repository.TrainPriceRepository;
import com.summerdev.travel.service.budget.BudgetService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 02.03.2022
 * Time: 19:30
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TrainPriceServiceImpl implements TrainPriceService {
    @NonNull TrainPriceRepository trainPriceRepository;

    @Qualifier("trainBudgetService")
    @NonNull BudgetService trainBudgetService;

    @Override
    public List<TrainPrice> getTrainPricesForTrip(Long totalBudget, ComfortType comfortType, GeoNameData departureCity) {
        Double budgetForTrain = trainBudgetService.getBudgetForTrip(totalBudget);

        return trainPriceRepository.findAllByTrainInfoDepartureCityAndCostLessThanAndComfortType(
                departureCity, budgetForTrain, comfortType);
    }

    @Override
    public List<GeoNameData> getArrivalCities(List<TrainPrice> trainPrices) {
        return trainPrices.stream()
                .map(price -> price.getTrainInfo().getArrivalCity())
                .distinct()
                .collect(Collectors.toList());
    }

}
