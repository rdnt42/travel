package com.summerdev.travel.service;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.repository.GeoNameRepository;
import com.summerdev.travel.repository.TravelComfortTypeRepository;
import com.summerdev.travel.response.TravelMapResponse;
import com.summerdev.travel.service.route.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TravelMapServiceImpl implements TravelMapService {

    private static final Logger log = LoggerFactory.getLogger(TravelMapServiceImpl.class);

    private final GeoNameRepository geoNameRepository;
    private final TravelComfortTypeRepository travelComfortTypeRepository;
    private final RouteService routeService;

    public TravelMapServiceImpl(GeoNameRepository geoNameRepository,
                                TravelComfortTypeRepository travelComfortTypeRepository, RouteService routeService) {
        this.geoNameRepository = geoNameRepository;
        this.travelComfortTypeRepository = travelComfortTypeRepository;
        this.routeService = routeService;
    }

    @Override
    public TravelMapResponse getTravelMap(String departureCityName, Long maxCost, Long travelComfortTypeId) {
        GeoName departureCity = geoNameRepository.findDistinctFirstByGeoNameRu(departureCityName)
                .orElse(null);

        if (departureCity == null) return new TravelMapResponse();

        TravelComfortType comfortType = travelComfortTypeRepository.findById(travelComfortTypeId)
                .orElseThrow(() -> new NullPointerException("Travel comfort type does not exist, id: " + travelComfortTypeId));

        return routeService.getTravelMap(departureCity, comfortType);
    }

}
