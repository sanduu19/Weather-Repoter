package com.opensource.weatherrepoter.service;

import com.opensource.weatherrepoter.dto.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeather(String city, String provider);
}
