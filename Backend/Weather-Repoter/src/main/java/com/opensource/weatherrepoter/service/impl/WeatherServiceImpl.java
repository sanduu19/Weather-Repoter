package com.opensource.weatherrepoter.service.impl;

import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.provider.WeatherProviderFactory;
import com.opensource.weatherrepoter.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service implementation for fetching weather data.
 * Delegates the request to the appropriate weather provider using the factory.
 */
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    // Logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    // Injects the WeatherProviderFactory to dynamically select the weather provider
    private final WeatherProviderFactory factory;

    /**
     * Fetches the current weather for a given city using the specified weather provider.
     *
     * @param city     the name of the city to get weather data for
     * @param provider the key of the weather provider (e.g., "weatherApi")
     * @return WeatherResponse containing the current weather details
     */
    @Override
    public WeatherResponse getWeather(String city, String provider) {
        logger.info("Fetching weather for city '{}' using provider '{}'", city, provider);
        WeatherResponse response = factory.getProvider(provider).getCurrentWeather(city);
        logger.info("Weather fetched successfully for city '{}'", city);
        return response;
    }
}
