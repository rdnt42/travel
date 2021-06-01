package com.summerdev.travel.service;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TrainInfo;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.repository.GeoNameRepository;
import com.summerdev.travel.repository.TrainInfoRepository;
import com.summerdev.travel.repository.TravelComfortTypeRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import com.summerdev.travel.response.TravelMapResponse;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelsResponse;
import com.summerdev.travel.service.api.hotellook.HotelLookService;
import com.summerdev.travel.service.api.tutu.TutuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TravelMapServiceImpl implements TravelMapService {

    private static final Logger log = LoggerFactory.getLogger(TravelMapServiceImpl.class);

    private final TutuService tutuService;
    private final HotelLookService hotelLookService;
    private final TrainInfoService trainInfoService;

    private final GeoNameRepository geoNameRepository;
    private final TutuStationRepository tutuStationRepository;
    private final TrainInfoRepository trainInfoRepository;
    private final TravelComfortTypeRepository travelComfortTypeRepository;

    public TravelMapServiceImpl(TutuService tutuService, HotelLookService hotelLookService,
                                TrainInfoService trainInfoService, GeoNameRepository geoNameRepository, TutuStationRepository tutuStationRepository,
                                TrainInfoRepository trainInfoRepository, TravelComfortTypeRepository travelComfortTypeRepository) {
        this.tutuService = tutuService;
        this.hotelLookService = hotelLookService;
        this.trainInfoService = trainInfoService;
        this.geoNameRepository = geoNameRepository;
        this.tutuStationRepository = tutuStationRepository;
        this.trainInfoRepository = trainInfoRepository;
        this.travelComfortTypeRepository = travelComfortTypeRepository;
    }

    @Override
    public TravelMapResponse getTravelMap(String departureCityName, Long maxCost, Long travelComfortTypeId) {
        long startTime = new Date().getTime();
        GeoName departureCity = geoNameRepository.findDistinctFirstByGeoNameRu(departureCityName);

        if (departureCity == null) {
            return new TravelMapResponse();
        }

        TravelComfortType comfortType = travelComfortTypeRepository.findById(travelComfortTypeId)
                .orElseThrow(() -> new NullPointerException("Travel comfort type does not exist, id: " + travelComfortTypeId));

        List<TrainInfo> trainInfos = trainInfoService.getInfoByDepartAndComfortType(departureCity, comfortType);

        Set<GeoName> arrivalCities = trainInfos.stream()
                .map(TrainInfo::getArrivalCity)
                .collect(Collectors.toSet());

        Map<GeoName, HotelLookHotelsResponse> lookHotelsResponseMap = new HashMap<>();

        for (GeoName arrivalCity : arrivalCities) {
            HotelLookHotelsResponse hotels = hotelLookService.getHotelsInfo(arrivalCity);
            if (hotels != null) {
                lookHotelsResponseMap.put(arrivalCity, hotels);
            }
        }

        log.info("Total get train info time: {}", new Date().getTime() - startTime);

        return null;
    }

}
