package com.geocode.search.controller;

import com.geocode.search.model.request.InputData;
import com.geocode.search.model.response.finder.GeoResults;
import com.geocode.search.service.FinderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FinderController {

	public final FinderService finderService;

	public enum levels {
		pointAddress, hospital, institute, parking, shopping
	}

	public FinderController(FinderService finderService) {
		this.finderService = finderService;
	}

	@PostMapping("/finder/{level}")
	public GeoResults getFinder(@PathVariable levels level, @RequestBody InputData inputData) {
		return finderService.findGeoElements(level, inputData);
	}
}
