package com.geocode.search.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

	private Integer code;
	private String description;

	public Status(Integer code, Definition definition) {
		this.code = code;
		this.description = definition.getDescription();
	}
}
