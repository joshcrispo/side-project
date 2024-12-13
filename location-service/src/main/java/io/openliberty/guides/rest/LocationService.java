package io.openliberty.guides.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.openliberty.guides.rest.Model.Location;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("locations")
public class LocationService {

    public static Map<String, Location> locations = new HashMap<>();

    static {
        locations.put("1", new Location("1", "Dublin", "Ireland"));
        locations.put("2", new Location("2", "New York", "USA"));
        locations.put("3", new Location("3", "Manila", "Philippines"));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Location> getAllUsers() {
        return locations.values();
    }

    @GET
    @Path("/{locId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocation(@PathParam("locId") String locId) {
        Location location = locations.get(locId);

        if (location != null) {
            return Response.ok(location).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Location not found").build();
    }
}
