package com.geocode.search.model.response.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeoElement {

	private String level;
	private String iso3;
	private Double longitude;
	private Double latitude;
	private Double distance;
	private Hierarchy hierarchy;
	private Other other;
}
