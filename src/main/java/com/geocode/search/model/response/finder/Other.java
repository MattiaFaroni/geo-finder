package com.geocode.search.model.response.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Other {

	private String place;
	private String phoneNumber;

	public Other(String place, String phoneNumber) {
		this.place = place;
		this.phoneNumber = phoneNumber;
	}
}
