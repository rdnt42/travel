package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.*;
import com.summerdev.travel.repository.TutuRouteRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 18:56
 */
@Service
public class RouteServiceImpl implements RouteService {
    private final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    private final TrainInfoService trainInfoService;
    private final HotelInfoService hotelInfoService;

    private final TutuRouteRepository tutuRouteRepository;
    private final TutuStationRepository tutuStationRepository;

    public RouteServiceImpl(TutuRouteRepository tutuRouteRepository, TutuStationRepository tutuStationRepository,
                            TrainInfoService trainInfoService, HotelInfoService hotelInfoService) {
        this.tutuRouteRepository = tutuRouteRepository;
        this.trainInfoService = trainInfoService;
        this.tutuStationRepository = tutuStationRepository;
        this.hotelInfoService = hotelInfoService;
    }

    @Override
    public void updateTrainsInfo() {
        List<TrainInfo> oldData = trainInfoService.getAllData();
        long updatedCount = 0;

        List<TutuRoute> routes = tutuRouteRepository.findAll();
        for (TutuRoute route : routes) {
            try {
                List<TrainInfo> created = trainInfoService.createTrainsInfo(route);
                updatedCount += created.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        trainInfoService.deleteOldTrainsInfo(oldData);

        logger.info("Trains info data updated. Updated count: {}, deleted count: {}", updatedCount, oldData.size());
    }

    @Override
    public void updateHotelsInfo() {
        List<HotelInfo> oldData = hotelInfoService.getAll();
        long updatedCount = 0;

        List<GeoName> cities = tutuStationRepository.findAll().stream()
                .map(TutuStation::getGeoName)
                .collect(Collectors.toList());

        for (GeoName city : cities) {
            try {
                List<HotelInfo> created = hotelInfoService.createHotelsInfo(city);
                updatedCount += created.size();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        hotelInfoService.deleteOldHotelsData(oldData);

        logger.info("Hotels info data updated. Updated count: {}, deleted count: {}", updatedCount, oldData.size());
    }


}
