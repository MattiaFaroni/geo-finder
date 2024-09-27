package com.geocode.search.model.request;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputData {

	private double longitude;
	private double latitude;
	private int candidates;

	public InputData(double longitude, double latitude, int candidates) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.candidates = candidates;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
