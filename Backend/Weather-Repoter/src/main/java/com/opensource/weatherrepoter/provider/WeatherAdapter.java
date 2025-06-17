package com.opensource.weatherrepoter.provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.opensource.weatherrepoter.dto.WeatherResponse;

public interface WeatherAdapter {
    WeatherResponse adapt(JsonNode node);
}
