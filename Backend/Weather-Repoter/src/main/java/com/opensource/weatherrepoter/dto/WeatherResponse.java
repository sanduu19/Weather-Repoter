package com.opensource.weatherrepoter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private String location;
    private String condition;
    private String conditionIconURL;
    private double temperatureC;
    private double windKph;
    private int humidity;
    private double uvIndex;
}
