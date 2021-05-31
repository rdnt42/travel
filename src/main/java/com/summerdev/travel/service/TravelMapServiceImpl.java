package com.summerdev.travel.service;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TutuRoute;
import com.summerdev.travel.entity.TutuStation;
import com.summerdev.travel.repository.GeoNameRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import com.summerdev.travel.response.TrainInfoResponse;
import com.summerdev.travel.response.TravelMapResponse;
import com.summerdev.travel.response.api.tutu.TutuRailwayCarriageResponse;
import com.summerdev.travel.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travel.response.api.tutu.TutuTripItemResponse;
import com.summerdev.travel.service.api.hotellook.HotelLookService;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelMapServiceImpl implements TravelMapService {

    private static final Logger log = LoggerFactory.getLogger(TravelMapServiceImpl.class);

    private final TutuService tutuService;
    private final HotelLookService hotelLookService;

    private final GeoNameRepository geoNameRepository;
    private final TutuStationRepository tutuStationRepository;

    public TravelMapServiceImpl(TutuService tutuService, HotelLookService hotelLookService,
                                GeoNameRepository geoNameRepository, TutuStationRepository tutuStationRepository) {
        this.tutuService = tutuService;
        this.hotelLookService = hotelLookService;
        this.geoNameRepository = geoNameRepository;
        this.tutuStationRepository = tutuStationRepository;
    }

    @Override
    public TravelMapResponse getTravelMap(String departureCity, Long maxCost) {
        long startTime = new Date().getTime();
        GeoName geoName = geoNameRepository.findDistinctFirstByGeoNameRu(departureCity);

        if (geoName == null) {
            return new TravelMapResponse();
        }

        List<TrainInfoResponse> trainInfoResponses = createTrainInfo(tutuService.getTrainsInfo(geoName));
        List<TrainInfoResponse> trainInfoResponsesFiltered = trainInfoResponses.stream()
                .filter(item -> item.getCost() < maxCost)
                .sorted(Comparator.comparing(TrainInfoResponse::getCost))
                .limit(10)
                .collect(Collectors.toList());

        log.info("Total get train info time: {}", new Date().getTime() - startTime);

        return null;
    }


    private List<TrainInfoResponse> createTrainInfo(List<TutuTrainsResponse> tutuTrainsResponses) {
        List<TrainInfoResponse> responses = new ArrayList<>();

        tutuTrainsResponses.forEach(info ->
                info.getTrips().forEach(trip ->
                        trip.getCategories().forEach(category -> {
                                    TrainInfoResponse trainInfo = createTrainInfo(trip, category);
                                    if (trainInfo != null) {
                                        responses.add(trainInfo);
                                    }
                                }
                        )));

        return responses;
    }

    private TrainInfoResponse createTrainInfo(TutuTripItemResponse trip, TutuRailwayCarriageResponse category) {
        TrainInfoResponse response = new TrainInfoResponse();
        response.setTravelTime(trip.getTravelTimeInSeconds());

        TutuStation arrivalStation = tutuStationRepository.findById(trip.getArrivalStation())
                .orElse(null);
        TutuStation departureStation = tutuStationRepository.findById(trip.getDepartureStation())
                .orElse(null);

        if (arrivalStation == null || departureStation == null) return null;

        response.setDepartureCity(departureStation.getGeoName());
        response.setArrivalCity(arrivalStation.getGeoName());

        response.setCost((long) category.getPrice());
        response.setCategoryName(category.getType());

        return response;
    }



}
