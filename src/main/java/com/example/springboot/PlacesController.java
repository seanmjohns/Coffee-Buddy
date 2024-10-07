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

    @Value("${google.api.key}")
    private String googleApiKey;

    @GetMapping("/")
    public String findPlaces(@RequestParam(value = "latitude", required = false) String latitude,
                 @RequestParam(value = "longitude", required = false) String longitude,
                 @RequestParam(value = "radius", required = false) String radius,
                 @RequestParam(value = "locationTypes", required = false) String[] locationTypes,
                 Model model) {

        model.addAttribute("longitude", longitude);
        model.addAttribute("latitude", latitude);
        model.addAttribute("radius", radius);
        model.addAttribute("googleApiKey", googleApiKey);
        
        List<String> types = locationTypes != null ? Arrays.asList(locationTypes) : new ArrayList<>();
        model.addAttribute("locationTypes", types);
        
        return "places";
    }
}