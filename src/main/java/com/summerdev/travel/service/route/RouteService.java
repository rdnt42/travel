package com.summerdev.travel.service.route;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 18:55
 */
public interface RouteService {
    /**
     * Update actual data from tutu.ru by trains
     */
    void updateTrainsInfo();

    void updateHotelsInfo();
}
