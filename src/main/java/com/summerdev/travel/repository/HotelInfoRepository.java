package com.summerdev.travel.repository;

import com.summerdev.travel.entity.HotelInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 01.06.2021
 * Time: 23:24
 */
public interface HotelInfoRepository extends JpaRepository<HotelInfo, Long> {

}
