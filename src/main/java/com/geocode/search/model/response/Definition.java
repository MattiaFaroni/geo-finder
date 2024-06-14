package com.geocode.search.model.response;

public enum Definition {
	POLYGON_FOUND("Polygon found"),
	POLYGON_NOT_FOUND("Polygon not found"),
	POLYGON_PROCESSING_FAILED("Polygon processing failed"),
	GEO_ELEMENTS_FOUND("Geo elements found"),
	GEO_ELEMENTS_NOT_FOUND("Geo elements not found");

	private final String description;

	Definition(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
