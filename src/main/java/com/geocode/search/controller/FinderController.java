package com.geocode.search.controller;

import com.geocode.search.model.levels.FinderLevel;
import com.geocode.search.model.request.FinderData;
import com.geocode.search.model.response.finder.GeoResults;
import com.geocode.search.service.FinderService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("finder")
public class FinderController {

    private final FinderService finderService;

    public FinderController(FinderService finderService) {
        this.finderService = finderService;
    }

    @PostMapping("/{level}")
    @Cacheable(value = "geoResult", key = "{ #root.methodName, #level, #finderData }")
    public GeoResults getFinder(@PathVariable FinderLevel level, @RequestBody FinderData finderData) {
        return finderService.findGeoElements(level, finderData);
    }
}
