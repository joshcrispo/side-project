package io.openliberty.guides.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("weather")
public class WeatherService {

    @GET
    @Path("/{locId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeatherByLocation(@PathParam("locId") String locId) {
        try {
            URL url = new URL("http://192.168.0.35:9091/api/v1/locations/" + locId);
            System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder locationResponse = new StringBuilder();
            String inputLine;

            while((inputLine = input.readLine()) != null) {
                locationResponse.append(inputLine);
            }

            input.close();

            if (connection.getResponseCode() == 200) {
                String weather = "{\"weather\": \"Sunny, 25C\", \"location\": " + locationResponse.toString() + "}";
                return Response.ok(weather).build();
            }

            return Response.status(Response.Status.NOT_FOUND).entity("Location not found, ID: " + locId).build();
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity("Failed to fetch weather data").build();
        }
    } 
}
