package com.geocode.search.model.request;

public class InputData {

	private double longitude;
	private double latitude;
	private int candidates;

	public InputData(double longitude, double latitude, int candidates) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.candidates = candidates;
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

	public int getCandidates() {
		return candidates;
	}

	public void setCandidates(int candidates) {
		this.candidates = candidates;
	}
}
