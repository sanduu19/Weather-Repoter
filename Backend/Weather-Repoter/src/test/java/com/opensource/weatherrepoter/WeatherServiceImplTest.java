package com.opensource.weatherrepoter;

import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.provider.WeatherProvider;
import com.opensource.weatherrepoter.provider.WeatherProviderFactory;
import com.opensource.weatherrepoter.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceImplTest {

    private WeatherProviderFactory factory;
    private WeatherProvider provider;
    private WeatherServiceImpl service;

    @BeforeEach
    void setUp() {
        factory = mock(WeatherProviderFactory.class);
        provider = mock(WeatherProvider.class);
        service = new WeatherServiceImpl(factory);
    }

    @Test
    void testGetWeather_ReturnsCorrectResponse() {
        // Arrange
        String city = "Colombo";
        String providerKey = "weatherApi";

        WeatherResponse mockResponse = new WeatherResponse();
        mockResponse.setLocation(city);
        mockResponse.setTemperatureC(30.0);
        mockResponse.setHumidity(70);
        mockResponse.setWindKph(10.0);
        mockResponse.setUvIndex(5.0);

        when(factory.getProvider(providerKey)).thenReturn(provider);
        when(provider.getCurrentWeather(city)).thenReturn(mockResponse);

        // Act
        WeatherResponse result = service.getWeather(city, providerKey);

        // Assert
        assertNotNull(result);
        assertEquals(city, result.getLocation());
        assertEquals(30.0, result.getTemperatureC());
        assertEquals(70, result.getHumidity());
        assertEquals(10.0, result.getWindKph());
        assertEquals(5.0, result.getUvIndex());

        verify(factory).getProvider(providerKey);
        verify(provider).getCurrentWeather(city);
    }

    @Test
    void testGetWeather_ThrowsException_WhenProviderNotFound() {
        // Arrange
        String city = "Colombo";
        String providerKey = "invalidProvider";

        when(factory.getProvider(providerKey)).thenThrow(new IllegalArgumentException("Provider not found"));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                service.getWeather(city, providerKey));

        assertEquals("Provider not found", exception.getMessage());
        verify(factory).getProvider(providerKey);
    }
}
