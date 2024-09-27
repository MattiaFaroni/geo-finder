package com.geocode.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "street")
public class Street implements Serializable {

	@Column(name = "geom")
	String geom;

	@Id
	@Column(name = "link_id")
	Long linkId;

	@Column(name = "name")
	String name;

	@Column(name = "type")
	String type;

	@Column(name = "name_base")
	String nameBase;

	@Column(name = "l_postcode")
	String leftPostcode;

	@Column(name = "r_postcode")
	String rightPostcode;

	@Column(name = "iso3")
	String iso3;
}
