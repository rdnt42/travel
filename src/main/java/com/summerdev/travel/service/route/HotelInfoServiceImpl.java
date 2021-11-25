package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.entity.route.HotelInfo;
import com.summerdev.travel.repository.HotelInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.summerdev.travel.entity.TravelComfortType.*;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 01.06.2021
 * Time: 20:39
 */
@Service
public class HotelInfoServiceImpl implements ITravelInfoService<HotelInfo>, HotelInfoService {
    private final Logger logger = LoggerFactory.getLogger(HotelInfoServiceImpl.class);

    private final List<Long> cheapList = Arrays.asList(1L, 2L);
    private final List<Long> comfortList = Arrays.asList(3L, 4L);
    private final List<Long> luxuryList = Collections.singletonList(5L);

    private final HotelInfoRepository hotelInfoRepository;

    public HotelInfoServiceImpl(HotelInfoRepository hotelInfoRepository) {
        this.hotelInfoRepository = hotelInfoRepository;
    }


    @Override
    public List<HotelInfo> getAllActualInfo() {
        return hotelInfoRepository.findAllByIsActualDataIsTrue();
    }

    @Override
    public List<HotelInfo> getInfo(List<GeoName> cities, TravelComfortType comfortType) {
        Long comfortTypeId = comfortType.getId();
        List<HotelInfo> hotelInfos = new ArrayList<>();

        if (comfortTypeId.equals(COMFORT_TYPE_CHEAP)) {
            hotelInfos = hotelInfoRepository.findAllByCityInAndStarsIn(cities, cheapList);
        } else if (comfortTypeId.equals(COMFORT_TYPE_COMFORT)) {
            hotelInfos = hotelInfoRepository.findAllByCityInAndStarsIn(cities, comfortList);
        } else if (comfortTypeId.equals(COMFORT_TYPE_LUXURY)) {
            hotelInfos = hotelInfoRepository.findAllByCityInAndStarsIn(cities, luxuryList);
        }

        return hotelInfos;
    }
}
