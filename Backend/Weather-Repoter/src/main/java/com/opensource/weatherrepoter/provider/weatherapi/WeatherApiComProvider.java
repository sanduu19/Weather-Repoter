package com.opensource.weatherrepoter.provider.weatherapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.exception.CityNotFoundException;
import com.opensource.weatherrepoter.provider.WeatherAdapter;
import com.opensource.weatherrepoter.provider.WeatherProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * This class is a concrete implementation of the WeatherProvider interface.
 * It fetches current weather data from the WeatherAPI.com service.
 */
@Component("weatherApi")
@RequiredArgsConstructor
public class WeatherApiComProvider implements WeatherProvider {

    private static final Logger logger = LoggerFactory.getLogger(WeatherApiComProvider.class);

    // Injecting the API key from the application properties (e.g., application.yml or .env)
    @Value("${weatherapi.api.key}")
    private String apiKey;

    // Injecting required dependencies using constructor injection (via Lombok's @RequiredArgsConstructor)
    private final RestTemplate restTemplate;
    private final WeatherAdapter weatherAdapter;

    // Used for parsing JSON responses
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Fetches current weather information for the given location using WeatherAPI.com.
     *
     * @param location the city or location for which weather data is required
     * @return a WeatherResponse DTO containing the adapted weather information
     * @throws CityNotFoundException if the location is not found or the API returns an error
     */
    @Override
    public WeatherResponse getCurrentWeather(String location) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;
        logger.info("Requesting weather data from WeatherAPI.com for location '{}'", location);

        try {
            // Make a GET request to the Weather API and map the response to a JsonNode
            JsonNode json = restTemplate.getForObject(url, JsonNode.class);
            logger.debug("Received weather data JSON: {}", json);

            // Adapt the raw JSON data into the standardized WeatherResponse DTO
            WeatherResponse response = weatherAdapter.adapt(json);
            logger.info("Successfully fetched weather data for location '{}'", location);
            return response;

        } catch (HttpClientErrorException e) {
            logger.error("HTTP error while fetching weather data for location '{}': {}", location, e.getStatusCode());

            try {
                // Attempt to extract a meaningful error message from the API response
                JsonNode errorBody = objectMapper.readTree(e.getResponseBodyAsString());
                String errorMessage = errorBody.path("error").path("message").asText("Unknown error");
                logger.error("Error response from WeatherAPI.com: {}", errorMessage);

                // Throw a custom exception with the extracted message
                throw new CityNotFoundException("Weather provider error: " + errorMessage);
            } catch (Exception parseEx) {
                logger.error("Error parsing error response body from WeatherAPI.com", parseEx);
                // Fallback error message if parsing fails
                throw new CityNotFoundException("Invalid location or weather provider issue.");
            }
        } catch (Exception e) {
            logger.error("Unexpected error while fetching weather data for location '{}'", location, e);
            throw e; // rethrow or wrap as needed
        }
    }
}
