package com.opensource.weatherrepoter.provider.weatherapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.provider.WeatherAdapter;
import com.opensource.weatherrepoter.provider.WeatherProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("weatherApi")
@RequiredArgsConstructor
public class WeatherApiComProvider implements WeatherProvider {

    @Value("${weatherapi.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private final WeatherAdapter weatherAdapter;

    @Override
    public WeatherResponse getCurrentWeather(String location) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;
        JsonNode json = restTemplate.getForObject(url, JsonNode.class);
        return weatherAdapter.adapt(json);
    }
}
