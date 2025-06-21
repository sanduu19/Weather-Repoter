package com.opensource.weatherrepoter.controller;

import com.opensource.weatherrepoter.dto.ServiceResponse;
import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.exception.CityNotFoundException;
import com.opensource.weatherrepoter.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WeatherController {

    private final WeatherService service;

    @Operation(summary = "Get weather by city", description = "Fetch weather data for a given city using a specified provider")
    @GetMapping
    public ResponseEntity<ServiceResponse<WeatherResponse>> getWeather(
            @Parameter(description = "Name of the city to get weather for", example = "Colombo")
            @RequestParam(defaultValue = "Colombo") String city,
            @Parameter(description = "Weather provider to use", example = "weatherApi")
            @RequestParam(defaultValue = "weatherApi") String provider) {
        try {
            WeatherResponse response = service.getWeather(city, provider);
            return ResponseEntity.ok(ServiceResponse.<WeatherResponse>builder()
                    .success(true)
                    .message("Weather fetched successfully")
                    .data(response)
                    .build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ServiceResponse.<WeatherResponse>builder()
                    .success(false)
                    .message("Invalid input: " + ex.getMessage())
                    .build());
        } catch (CityNotFoundException ex) {
            return ResponseEntity.badRequest().body(ServiceResponse.<WeatherResponse>builder()
                    .success(false)
                    .message(ex.getMessage())
                    .build());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ServiceResponse.<WeatherResponse>builder()
                    .success(false)
                    .message("Internal Server Error. Please try again later.")
                    .build());
        }
    }
}
