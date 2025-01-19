package com.geocode.search.controller;

import com.geocode.search.model.levels.PolygonLevel;
import com.geocode.search.model.response.polygon.PolygonResult;
import com.geocode.search.service.PolygonService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("polygon")
public class PolygonController {

    private final PolygonService polygonService;

    public PolygonController(PolygonService polygonService) {
        this.polygonService = polygonService;
    }

    // spotless:off
	@GetMapping("/description/{level}")
	@Cacheable(value = "polygonName", key = "{ #root.methodName, #polygonName, #level, #iso3 }")
	public PolygonResult getPolygon(@PathVariable PolygonLevel level, @RequestParam(name = "name") String polygonName, @RequestParam(name = "iso3") String iso3) {
		return polygonService.findGeoJson(level, polygonName, iso3);
	}

	@GetMapping("/coordinates/{level}")
	@Cacheable(value = "polygonCoordinates", key = "{ #root.methodName, #level, #longitude, #latitude }")
	public PolygonResult getPolygon(@PathVariable PolygonLevel level, @RequestParam(name = "longitude") double longitude, @RequestParam(name = "latitude") double latitude) {
		return polygonService.findGeoJson(level, longitude, latitude);
	}
	// spotless:on
}
