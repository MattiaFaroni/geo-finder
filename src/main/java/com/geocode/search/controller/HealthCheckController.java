package com.geocode.search.controller;

import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private final BuildProperties buildProperties;

    public HealthCheckController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return String.format("API version n° %s is running.", buildProperties.getVersion());
    }
}
