package com.geocode.search.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompositeKey {

	private Long polygon_id;
	private Long area_id;

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
