package com.geocode.search.model.response.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Hierarchy {

	private String region;
	private String province;
	private String municipality;
	private String settlement;
	private String streetType;
	private String streetName;
	private String street;
	private String postcode;
	private String houseNumber;
}
