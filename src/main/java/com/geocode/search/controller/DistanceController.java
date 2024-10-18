package com.geocode.search.controller;

import com.geocode.search.model.request.DistanceData;
import com.geocode.search.model.response.distance.DistanceResult;
import com.geocode.search.service.DistanceService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistanceController {

	private final DistanceService distanceService;

	public DistanceController(DistanceService distanceService) {
		this.distanceService = distanceService;
	}

	@PostMapping("/distance")
	@Cacheable(value = "distanceCoordinates", key = "{ #root.methodName, #distanceData }")
	public DistanceResult distance(@RequestBody DistanceData distanceData) {
		return distanceService.calculateDistance(distanceData);
	}
}
