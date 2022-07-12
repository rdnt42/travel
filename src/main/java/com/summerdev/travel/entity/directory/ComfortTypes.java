package com.summerdev.travel.entity.directory;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 19:59
 */
public enum ComfortTypes {
    COMFORT_TYPE_CHEAP,
    COMFORT_TYPE_COMFORT,
    COMFORT_TYPE_LUXURY;

    public static ComfortTypes fromString(String text) {
        for (ComfortTypes comfortType : ComfortTypes.values()) {
            if(comfortType.toString().equalsIgnoreCase(text)) {
                return comfortType;
            }
        }
        return null;
    }
}
