package com.opensource.weatherrepoter.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class WeatherProviderFactory {

    private final Map<String, WeatherProvider> providers;

    public WeatherProvider getProvider(String key) {
        WeatherProvider provider = providers.get(key);
        if (provider == null) throw new IllegalArgumentException("No such provider: " + key);
        return provider;
    }
}
