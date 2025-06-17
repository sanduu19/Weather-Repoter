package com.opensource.weatherrepoter.provider;

import com.opensource.weatherrepoter.dto.WeatherResponse;

public interface WeatherProvider {
    WeatherResponse getCurrentWeather(String location);
}
