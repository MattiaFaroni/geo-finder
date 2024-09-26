package com.geocode.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@IdClass(CompositeKey.class)
public class Territory implements Serializable {

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
}
