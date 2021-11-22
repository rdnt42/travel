package com.summerdev.travel.response;

import com.summerdev.travel.entity.route.HotelInfo;
import com.summerdev.travel.entity.route.TrainInfo;

public class TravelMapItemResponse {
    private TrainInfo trainInfo;

    private HotelInfo hotelInfo;

    public TravelMapItemResponse() {
    }

    public TravelMapItemResponse(TrainInfo trainInfo, HotelInfo hotelInfo) {
        this.trainInfo = trainInfo;
        this.hotelInfo = hotelInfo;
    }

    public TrainInfo getTrainInfo() {
        return trainInfo;
    }

    public void setTrainInfo(TrainInfo trainInfo) {
        this.trainInfo = trainInfo;
    }

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }
}
