package com.opensource.weatherrepoter.provider.weatherapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.provider.WeatherAdapter;
import org.springframework.stereotype.Component;

/**
 * Adapter implementation for WeatherAPI.com response.
 * Converts raw JSON response into the standardized WeatherResponse DTO.
 */
@Component("weatherApiAdapter")
public class WeatherApiComAdapter implements WeatherAdapter {

    /**
     * Adapts the JSON response from WeatherAPI.com into a WeatherResponse DTO.
     *
     * @param node the root JsonNode object received from the Weather API
     * @return WeatherResponse containing mapped weather details
     */
    @Override
    public WeatherResponse adapt(JsonNode node) {
        return new WeatherResponse(
                // Extract city/location name
                node.get("location").get("name").asText(),

                // Extract weather condition description (e.g., "Partly cloudy")
                node.get("current").get("condition").get("text").asText(),

                // Extract icon URL representing current weather condition
                node.get("current").get("condition").get("icon").asText(),

                // Extract current temperature in Celsius
                node.get("current").get("temp_c").asDouble(),

                // Extract wind speed in kilometers per hour
                node.get("current").get("wind_kph").asDouble(),

                // Extract humidity percentage
                node.get("current").get("humidity").asInt(),

                // Extract UV index
                node.get("current").get("uv").asDouble()
        );
    }
}
