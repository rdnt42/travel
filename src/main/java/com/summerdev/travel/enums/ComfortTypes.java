package com.summerdev.travel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 19:59
 */
@AllArgsConstructor
@Getter
public enum ComfortTypes {
    COMFORT_TYPE_CHEAP(1),
    COMFORT_TYPE_COMFORT(2),
    COMFORT_TYPE_LUXURY(3);

    private final Integer id;

    public static ComfortTypes fromString(String text) {
        for (ComfortTypes comfortType : ComfortTypes.values()) {
            if(comfortType.toString().equalsIgnoreCase(text)) {
                return comfortType;
            }
        }
        return null;
    }
}
