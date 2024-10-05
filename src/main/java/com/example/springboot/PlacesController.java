package com.example.springboot;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlacesController {

    @Value("${google.api.key}")
    private String googleApiKey; // Add your Google API Key to application.properties

    @GetMapping("/")
    public String findPlaces(@RequestParam(value = "latitude", required = false) String latitude,
                 @RequestParam(value = "longitude", required = false) String longitude,
                 @RequestParam(value = "radius", required = false) String radius, Model model,
                 @RequestParam(value = "locationTypes", required = false) String locationTypes) {

        model.addAttribute("longitude", longitude);
        model.addAttribute("latitude", latitude);
        model.addAttribute("radius", radius);
        model.addAttribute("googleApiKey", googleApiKey);
        model.addAttribute("locationTypes", locationTypes != null ? locationTypes : new ArrayList<String>());
        return "places";
    }
}