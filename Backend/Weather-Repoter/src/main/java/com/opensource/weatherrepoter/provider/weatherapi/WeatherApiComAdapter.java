package com.opensource.weatherrepoter.provider.weatherapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.provider.WeatherAdapter;
import org.springframework.stereotype.Component;

@Component("weatherApiAdapter")
public class WeatherApiComAdapter implements WeatherAdapter {

    @Override
    public WeatherResponse adapt(JsonNode node) {
        return new WeatherResponse(
            node.get("location").get("name").asText(),
            node.get("current").get("condition").get("text").asText(),
            node.get("current").get("condition").get("icon").asText(),
            node.get("current").get("temp_c").asDouble(),
            node.get("current").get("wind_kph").asDouble(),
            node.get("current").get("humidity").asInt(),
            node.get("current").get("uv").asDouble()
        );
    }
}
