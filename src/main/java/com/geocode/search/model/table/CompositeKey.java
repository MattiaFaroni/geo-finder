package com.geocode.search.model.table;

public class CompositeKey {

	private Long polygon_id;
	private Long area_id;

	public Long getPolygonId() {
		return polygon_id;
	}

	public void setPolygonId(Long polygon_id) {
		this.polygon_id = polygon_id;
	}

	public Long getAreaId() {
		return area_id;
	}

	public void setAreaId(Long area_id) {
		this.area_id = area_id;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
