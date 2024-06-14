package com.geocode.search.model.table;

import jakarta.persistence.*;

@Entity
@Table(name = "pointaddress")
public class PointAddress {

	@Id
	@Column(name = "id")
	Long id;
	@Column(name = "geom")
	String geom;
	@Column(name = "link_id")
	Long linkId;
	@Column(name = "pt_addr_id")
	Long personalId;
	@Column(name = "side")
	String side;
	@Column(name = "feature_id")
	Long featureId;
	@Column(name = "iso3")
	String iso3;
	@Column(name = "address")
	String address;
	@Column(name = "disp_lon")
	double longitude;
	@Column(name = "disp_lat")
	double latitude;

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

	public Long getPersonalId() {
		return personalId;
	}

	public void setPersonalId(Long personalId) {
		this.personalId = personalId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Long getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
