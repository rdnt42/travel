package com.summerdev.travel.service.route;

import com.summerdev.travel.entity.GeoNameData;
import com.summerdev.travel.entity.hotel.HotelInfo;
import com.summerdev.travel.repository.HotelInfoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
public class HotelInfoServiceImpl implements HotelInfoService {

    private final HotelInfoRepository hotelInfoRepository;

    @Override
    public List<HotelInfo> getInfo(List<GeoNameData> cities, String comfortType) {
        return null;
//        return hotelInfoRepository.findAllByCityAndPrice(cities, 1000.0);
//        return hotelInfoRepository.findAllByCityAndPrice(cities);

//        List<HotelInfo> hotelInfos = new ArrayList<>();
//
//        if (comfortType.equals(COMFORT_TYPE_CHEAP.toString())) {
//            hotelInfos = hotelInfoRepository.findAllByCityInAndStarsIn(cities, cheapList);
//        } else if (comfortType.equals(COMFORT_TYPE_COMFORT.toString())) {
//            hotelInfos = hotelInfoRepository.findAllByCityInAndStarsIn(cities, comfortList);
//        } else if (comfortType.equals(COMFORT_TYPE_LUXURY.toString())) {
//            hotelInfos = hotelInfoRepository.findAllByCityInAndStarsIn(cities, luxuryList);
//        }
//
//        return hotelInfos;
    }
}
