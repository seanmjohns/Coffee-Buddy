package com.example.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlacesController {

    // Inject Google API Key from the application properties
    @Value("${google.api.key}")
    private String googleApiKey;

    // Default values for latitude, longitude, search radius, and location types
    private static final String DEFAULT_LATITUDE = "33.7709454";
    private static final String DEFAULT_LONGITUDE = "-84.3942562";
    private static final String DEFAULT_RADIUS = "500";
    private static final Integer DEFAULT_LIMIT = 10;
    private static final List<String> DEFAULT_LOCATION_TYPES = Arrays.asList("restaurant");

    //Sets values from http request which overrides defaults
    @GetMapping("/")
    public String findPlaces(@RequestParam(value = "latitude", required = false) String latitude,
                 @RequestParam(value = "longitude", required = false) String longitude,
                 @RequestParam(value = "radius", required = false) String radius,
                 @RequestParam(value = "limit", required = false) Integer limit,
                 @RequestParam(value = "locationTypes", required = false) String[] locationTypes,
                 Model model) {

        model.addAttribute("latitude", latitude != null ? latitude : DEFAULT_LATITUDE);
        model.addAttribute("longitude", longitude != null ? longitude : DEFAULT_LONGITUDE);
        model.addAttribute("radius", radius != null ? radius : DEFAULT_RADIUS);
        model.addAttribute("limit", limit != null ? limit : DEFAULT_LIMIT);
        model.addAttribute("googleApiKey", googleApiKey);
        
        // Handle location types: if provided, convert them to a list; otherwise, use the default types
        List<String> types = locationTypes != null ? Arrays.asList(locationTypes) : DEFAULT_LOCATION_TYPES;
        model.addAttribute("locationTypes", types);
        
        // Return the view name "places", which will be resolved to a Thymeleaf template (e.g., places.html)
        return "places";
    }
}
