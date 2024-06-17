package com.geocode.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GeoFinderApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GeoFinderApplication.class, args);
	}
}
