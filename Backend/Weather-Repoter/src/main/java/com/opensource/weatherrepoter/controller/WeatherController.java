package com.opensource.weatherrepoter.controller;

import com.opensource.weatherrepoter.dto.WeatherResponse;
import com.opensource.weatherrepoter.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WeatherController {

    private final WeatherService service;

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestParam(defaultValue = "Colombo") String city,
            @RequestParam(defaultValue = "weatherApi") String provider) {
        try {
            WeatherResponse response = service.getWeather(city, provider);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
