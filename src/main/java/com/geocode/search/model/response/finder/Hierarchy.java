package com.geocode.search.model.response.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Hierarchy implements Serializable {

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
