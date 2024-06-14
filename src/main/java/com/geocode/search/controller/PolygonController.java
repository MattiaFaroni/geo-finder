package com.geocode.search.controller;

import com.geocode.search.model.response.polygon.Polygon;
import com.geocode.search.service.PolygonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PolygonController {

	public final PolygonService polygonService;

	public enum levels {
		region, province, municipality, settlement
	}

	public PolygonController(PolygonService polygonService) {
		this.polygonService = polygonService;
	}

	@GetMapping("/polygon/description/{level}")
	public Polygon getPolygon(@PathVariable levels level, @RequestParam(name = "name") String polygonName,
			@RequestParam(name = "iso3") String iso3) {
		return polygonService.findGeoJson(level, polygonName, iso3);
	}

	@GetMapping("/polygon/coordinates/{level}")
	public Polygon getPolygon(@PathVariable levels level, @RequestParam(name = "longitude") double longitude,
			@RequestParam(name = "latitude") double latitude) {
		return polygonService.findGeoJson(level, longitude, latitude);
	}
}
