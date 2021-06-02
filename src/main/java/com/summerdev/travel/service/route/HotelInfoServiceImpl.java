package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoName;
import com.summerdev.travel.entity.HotelInfo;
import com.summerdev.travel.repository.HotelInfoRepository;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travel.response.api.hotellook.HotelLookHotelsResponse;
import com.summerdev.travel.service.api.hotellook.HotelLookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 01.06.2021
 * Time: 20:39
 */
@Service
public class HotelInfoServiceImpl implements HotelInfoService {

    private final HotelLookService hotelLookService;
    private final HotelInfoRepository hotelInfoRepository;

    public HotelInfoServiceImpl(HotelLookService hotelLookService, HotelInfoRepository hotelInfoRepository) {
        this.hotelLookService = hotelLookService;
        this.hotelInfoRepository = hotelInfoRepository;
    }


    @Override
    public List<HotelInfo> getAll() {
        return hotelInfoRepository.findAll();
    }

    @Override
    public void deleteOldHotelsData(List<HotelInfo> oldData) {
        hotelInfoRepository.deleteAll(oldData);
    }

    @Override
    public List<HotelInfo> createHotelsInfo(GeoName city) {
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
}
