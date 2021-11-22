package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.TravelComfortType;
import com.summerdev.travel.entity.route.HotelInfo;
import com.summerdev.travel.entity.tutu.TutuStation;
import com.summerdev.travel.repository.HotelInfoRepository;
import com.summerdev.travel.repository.TutuStationRepository;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelsResponse;
import com.summerdev.travel.service.api.hotellook.HotelLookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    private final HotelLookService hotelLookService;
    private final HotelInfoRepository hotelInfoRepository;
    private final TutuStationRepository tutuStationRepository;

    public HotelInfoServiceImpl(HotelLookService hotelLookService, HotelInfoRepository hotelInfoRepository,
                                TutuStationRepository tutuStationRepository) {
        this.hotelLookService = hotelLookService;
        this.hotelInfoRepository = hotelInfoRepository;
        this.tutuStationRepository = tutuStationRepository;
    }


    @Override
    public List<HotelInfo> getAllActualInfo() {
        return hotelInfoRepository.findAllByIsActualDataIsTrue();
    }

    @Override
    public void updateTravelInfo() {
        long updatedCount = 0;

        List<GeoName> cities = tutuStationRepository.findAll().stream()
                .map(TutuStation::getGeoName)
                .collect(Collectors.toList());

        for (GeoName city : cities) {
            try {
                long createdSize = createHotelsInfo(city).size();
                updatedCount += createdSize;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        logger.info("Hotels info data updated. Updated count: {}", updatedCount);

    }

    private List<HotelInfo> createHotelInfoFromHotelsResponse(
            HotelLookHotelsResponse response, int totalDaysCount, GeoName city) {
        if (response == null) return new ArrayList<>();

        List<HotelInfo> hotelInfos = new ArrayList<>();

        for (HotelLookHotelResponse hotel : response.getHotels()) {
            HotelInfo lowCost = getHotelInfoWithoutPrice(hotel, city);
            lowCost.setCost(getCost(totalDaysCount, hotel.getPricePercentile().getSmallPrice()));

            hotelInfos.add(lowCost);
        }

        return hotelInfos;
    }

    private HotelInfo getHotelInfoWithoutPrice(HotelLookHotelResponse response, GeoName city) {
        HotelInfo hotelInfo = new HotelInfo();

        hotelInfo.setCity(city);
        hotelInfo.setStars(response.getStars());

        return hotelInfo;
    }

    private Long getCost(int totalDaysCount, Double fullCost) {
        return (long) (fullCost / totalDaysCount);
    }

    private List<HotelInfo> createHotelsInfo(GeoName city) {
        int totalDaysCount = 30;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, totalDaysCount);
        Date endDate = calendar.getTime();

        HotelLookHotelsResponse response = hotelLookService.getHotelsInfo(city, startDate, endDate);

        List<HotelInfo> hotelInfos = createHotelInfoFromHotelsResponse(response, totalDaysCount, city);
        hotelInfoRepository.saveAll(hotelInfos);

        return hotelInfos;
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
