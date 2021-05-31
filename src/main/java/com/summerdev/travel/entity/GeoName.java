package com.summerdev.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 28.05.2021
 * Time: 23:26
 */
@Entity
@Table(name = "geo_names")
public class GeoName implements Serializable {
    @Id
    @Column(name = "geo_name_id")
    private Long id;

    private String geoName;

    private String geoNameRu;

    private Float latitude;

    private Float longitude;

    private String countryCode;

    private String timezone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeoName() {
        return geoName;
    }

    public void setGeoName(String geoName) {
        this.geoName = geoName;
    }

    public String getGeoNameRu() {
        return geoNameRu;
    }

    public void setGeoNameRu(String geoNameRu) {
        this.geoNameRu = geoNameRu;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
