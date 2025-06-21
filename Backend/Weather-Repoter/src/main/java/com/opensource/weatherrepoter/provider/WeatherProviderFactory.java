package com.opensource.weatherrepoter.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Factory class responsible for providing the appropriate WeatherProvider implementation
 * based on a given provider key (e.g., "weatherApi").
 */
@Component
@RequiredArgsConstructor
public class WeatherProviderFactory {

    // A map of provider beans injected by Spring, where the key is the bean name (e.g., "weatherApi")
    private final Map<String, WeatherProvider> providers;

    /**
     * Returns a WeatherProvider instance based on the given key.
     *
     * @param key the identifier of the desired weather provider (e.g., "weatherApi")
     * @return the corresponding WeatherProvider implementation
     * @throws IllegalArgumentException if no provider is found for the given key
     */
    public WeatherProvider getProvider(String key) {
        WeatherProvider provider = providers.get(key);

        // If the provider is not found, throw a descriptive exception
        if (provider == null) {
            throw new IllegalArgumentException("No such provider: " + key);
        }

        return provider;
    }
}
