package com.summerdev.travel.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 20.01.2022
 * Time: 22:26 */
@Getter
@Table(name = "geo_names")
@Entity
public class GeoNameData implements Serializable {
    @Id
    @Column(name = "geo_name_id")
    private Long id;

    private String geoName;

    private String geoNameRu;

    private Float latitude;

    private Float longitude;

    private String countryCode;

    private String timezone;
}
