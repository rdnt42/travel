package com.summerdev.travel.entity.directory;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 19:59
 */
public enum ComfortType {
    COMFORT_TYPE_CHEAP,
    COMFORT_TYPE_COMFORT,
    COMFORT_TYPE_LUXURY;

    public static ComfortType fromString(String text) {
        for (ComfortType comfortType : ComfortType.values()) {
            if(comfortType.toString().equalsIgnoreCase(text)) {
                return comfortType;
            }
        }
        return null;
    }
}
