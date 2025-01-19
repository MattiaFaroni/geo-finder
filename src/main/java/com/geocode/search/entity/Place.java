package com.geocode.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class Place implements Serializable {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "geom")
    String geom;

    @Column(name = "link_id")
    Long linkId;

    @Column(name = "poi_id")
    Long poiId;

    @Column(name = "poi_name")
    String poiName;

    @Column(name = "poi_st_num")
    String poiStreetNumber;

    @Column(name = "st_name")
    String streetName;

    @Column(name = "iso3")
    String iso3;

    @Column(name = "ph_number")
    String phoneNumber;
}
