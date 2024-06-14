package com.geocode.search.model.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "street")
public class Street {

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

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long link_id) {
		this.linkId = link_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNameBase() {
		return nameBase;
	}

	public void setNameBase(String nameBase) {
		this.nameBase = nameBase;
	}

	public String getLeftPostcode() {
		return leftPostcode;
	}

	public void setLeftPostcode(String leftPostcode) {
		this.leftPostcode = leftPostcode;
	}

	public String getRightPostcode() {
		return rightPostcode;
	}

	public void setRightPostcode(String rightPostcode) {
		this.rightPostcode = rightPostcode;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
}
