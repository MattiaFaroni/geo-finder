package com.geocode.search.model.response.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geocode.search.model.response.Status;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeoResults {

	private List<GeoElement> candidates;
	private Status status;

	public GeoResults(List<GeoElement> candidates, Status status) {
		this.candidates = candidates;
		this.status = status;
	}
}
