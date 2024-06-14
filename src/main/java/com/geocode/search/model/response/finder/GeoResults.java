package com.geocode.search.model.response.finder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geocode.search.model.response.Status;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeoResults {

	private List<GeoElement> candidates;
	private Status status;

	public GeoResults(List<GeoElement> candidates, Status status) {
		this.candidates = candidates;
		this.status = status;
	}

	public List<GeoElement> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<GeoElement> candidates) {
		this.candidates = candidates;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
