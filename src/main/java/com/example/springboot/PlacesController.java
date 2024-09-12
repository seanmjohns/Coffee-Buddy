package com.example.springboot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class PlacesController {

    @Value("${google.api.key}")
    private String googleApiKey; // Add your Google API Key to application.properties

    // Handles GET requests to /places endpoint
    @GetMapping("/places")
    public String findPlaces(@RequestParam(value = "latitude", required = false) String latitude,
                 @RequestParam(value = "longitude", required = false) String longitude,
                 @RequestParam(value = "radius", required = false) String radius, Model model) {

        // Constructs the Google Places API URL using latitude, longitude, radius, and API key
        String url = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&radius=%s&key=%s",
                latitude, longitude, radius, googleApiKey);

        // RestTemplate is used to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Makes a GET request to the Google Places API and stores the response in a Map
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> response = responseEntity.getBody();
        System.out.println(response.toString());

        //Loops through the places from the results and adds them to the
        //places list
        List<Map<String, String>> places = new ArrayList<>();
        if (response != null && response.containsKey("results")) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
            for (Map<String, Object> place : results) {
                Map<String, String> placeInfo = new HashMap<>();
                placeInfo.put("name", (String) place.get("name"));
                placeInfo.put("address", (String) place.get("vicinity"));
                places.add(placeInfo);
            }
        }

        //Adds lat,long,radius to places so that they can be viewed
        model.addAttribute("longitude", longitude);
        model.addAttribute("latitude", latitude);
        model.addAttribute("radius", radius);

        model.addAttribute("places", places);
        return "places";
    }
}