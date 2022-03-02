package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.directory.ComfortType;
import com.summerdev.travel.entity.hotel.HotelPrice;
import com.summerdev.travel.repository.HotelPriceRepository;
import com.summerdev.travel.service.budget.BudgetService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 01.06.2021
 * Time: 20:39
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class HotelPriceServiceImpl implements HotelPriceService {
    @NonNull HotelPriceRepository hotelPriceRepository;

    @Qualifier("hotelBudgetService")
    @NonNull BudgetService budgetService;

    @Override
    public List<HotelPrice> getHotelsPricesForTrip(List<GeoNameData> cities, Long totalBudget, ComfortType comfortType) {
        Double budgetForTrain = budgetService.getBudgetForTrip(totalBudget);

        return hotelPriceRepository.findAllByHotelInfoCityInAndCostLessThanAndComfortType(
                cities, budgetForTrain, comfortType);
    }


}
