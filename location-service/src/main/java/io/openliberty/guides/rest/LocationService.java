package io.openliberty.guides.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.openliberty.guides.rest.Model.Location;

@RestController
public class LocationService {

    // In-memory data for location
    public static Map<String, Location> locations = new HashMap<>();

    static {
        locations.put("1", new Location("1", "Dublin", "Ireland"));
        locations.put("2", new Location("2", "New York", "USA"));
        locations.put("3", new Location("3", "Manila", "Philippines"));
    }

    // Get all locations
    @GetMapping("/v1/locations")
    public Collection<Location> getAllLocations() {
        return locations.values();
    }

    // Get a specific location by ID
    @GetMapping("/v1/locations/{locId}")
    public Location getLocation(@PathVariable String locId) {
        return locations.get(locId);
    }
}