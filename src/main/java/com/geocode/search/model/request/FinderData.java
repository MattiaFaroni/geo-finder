package com.geocode.search.model.request;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinderData {

	private double longitude;
	private double latitude;
	private int candidates;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
