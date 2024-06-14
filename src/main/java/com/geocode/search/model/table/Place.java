package com.geocode.search.model.table;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Place {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public Long getPoiId() {
		return poiId;
	}

	public void setPoiId(Long poiId) {
		this.poiId = poiId;
	}

	public String getPoiName() {
		return poiName;
	}

	public void setPoiName(String poi_name) {
		this.poiName = poi_name;
	}

	public String getPoiStreetNumber() {
		return poiStreetNumber;
	}

	public void setPoiStreetNumber(String poi_st_num) {
		this.poiStreetNumber = poi_st_num;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String st_name) {
		this.streetName = st_name;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String ph_number) {
		this.phoneNumber = ph_number;
	}
}
