package com.geocode.search.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
}
