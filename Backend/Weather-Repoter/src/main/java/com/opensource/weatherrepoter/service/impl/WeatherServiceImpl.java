package com.opensource.weatherrepoter.service.impl;

import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.provider.WeatherProviderFactory;
import com.opensource.weatherrepoter.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherProviderFactory factory;

    public WeatherResponse getWeather(String city, String provider) {
        return factory.getProvider(provider).getCurrentWeather(city);
    }
}