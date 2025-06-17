package com.opensource.weatherrepoter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherResponse {
    private String location;
    private double temperatureC;
    private double windKph;
    private int humidity;
    private double uvIndex;
}
