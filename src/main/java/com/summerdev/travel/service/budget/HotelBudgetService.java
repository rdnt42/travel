package com.summerdev.travel.service.budget;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 02.03.2022
 * Time: 19:51
 */
@Service("hotelBudgetService")
public class HotelBudgetService implements BudgetService {

    @Override
    public Double getBudgetForTrip(Long totalBudget) {
        return totalBudget * 0.5;
    }
}
