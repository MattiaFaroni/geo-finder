package com.geocode.search.controller;

import com.geocode.search.model.levels.PolygonLevel;
import com.geocode.search.model.response.polygon.Polygon;
import com.geocode.search.service.PolygonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("polygon")
public class PolygonController {

	private final PolygonService polygonService;

	public PolygonController(PolygonService polygonService) {
		this.polygonService = polygonService;
	}

	@GetMapping("/description/{level}")
	public Polygon getPolygon(
			@PathVariable PolygonLevel level,
			@RequestParam(name = "name") String polygonName,
			@RequestParam(name = "iso3") String iso3) {
		return polygonService.findGeoJson(level, polygonName, iso3);
	}

	@GetMapping("/coordinates/{level}")
	public Polygon getPolygon(
			@PathVariable PolygonLevel level,
			@RequestParam(name = "longitude") double longitude,
			@RequestParam(name = "latitude") double latitude) {
		return polygonService.findGeoJson(level, longitude, latitude);
	}
}
