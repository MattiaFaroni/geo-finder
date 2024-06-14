package com.geocode.search.model.table;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@IdClass(CompositeKey.class)
public class Territory {

	@Column(name = "geom")
	String geom;
	@Id
	@Column(name = "polygon_id")
	Long polygon_id;
	@Id
	@Column(name = "area_id")
	Long area_id;
	@Column(name = "polygon_nm")
	String polygonName;
	@Column(name = "iso3")
	String iso3;

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public Long getPolygon_id() {
		return polygon_id;
	}

	public void setPolygon_id(Long polygon_id) {
		this.polygon_id = polygon_id;
	}

	public Long getArea_id() {
		return area_id;
	}

	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}

	public String getPolygonName() {
		return polygonName;
	}

	public void setPolygonName(String polygon_nm) {
		this.polygonName = polygon_nm;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
}
