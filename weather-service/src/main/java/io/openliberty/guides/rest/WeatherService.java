package io.openliberty.guides.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherService {

    private final RestTemplate restTemplate;

    // Inject RestTemplate
    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/weather/{locId}")
    public ResponseEntity<String> getWeatherByLocation(@PathVariable("locId") String locId) {
        try {
            String location = restTemplate.getForObject("http://localhost:8080/v1/locations/" + locId, String.class);

            if (location != null) {
                String weatherJson = "{\"weather\": \"Sunny, 25C\", \"location\": " + location.toString() + "}";

                return ResponseEntity.ok()
                                 .header("Content-Type", "application/json")
                                 .body(weatherJson);
            } else {
                // If location not found, send a failure message
                return ResponseEntity.status(404).body("Location not found for ID: " + locId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to fetch weather data");
        }
    }
}
