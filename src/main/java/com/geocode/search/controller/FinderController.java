package com.geocode.search.controller;

import com.geocode.search.model.levels.FinderLevel;
import com.geocode.search.model.request.InputData;
import com.geocode.search.model.response.finder.GeoResults;
import com.geocode.search.service.FinderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("finder")
public class FinderController {

	private final FinderService finderService;

	public FinderController(FinderService finderService) {
		this.finderService = finderService;
	}

	@PostMapping("/{level}")
	public GeoResults getFinder(@PathVariable FinderLevel level, @RequestBody InputData inputData) {
		return finderService.findGeoElements(level, inputData);
	}
}
