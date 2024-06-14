package com.geocode.search.model.response.polygon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geocode.search.model.response.Status;
import java.math.BigDecimal;
import java.util.List;

public class Polygon {

	private List<List<List<List<BigDecimal>>>> coordinates;
	private Status status;

	@JsonProperty("coordinates")
	public List<List<List<List<BigDecimal>>>> getCoordinates() {
		return coordinates;
	}

	@JsonProperty("coordinates")
	public void setCoordinates(List<List<List<List<BigDecimal>>>> coordinates) {
		this.coordinates = coordinates;
	}

	@JsonProperty("status")
	public Status getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Status status) {
		this.status = status;
	}

	public Polygon(List<List<List<List<BigDecimal>>>> coordinates, Status status) {
		this.coordinates = coordinates;
		this.status = status;
	}
}
