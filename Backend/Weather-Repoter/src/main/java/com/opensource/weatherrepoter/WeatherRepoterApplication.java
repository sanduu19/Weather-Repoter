package com.opensource.weatherrepoter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@OpenAPIDefinition(
        info = @Info(
                title = "Weather Reporter API",
                version = "1.0",
                description = "API for fetching weather information using different providers"
        )
)
@SpringBootApplication
public class WeatherRepoterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherRepoterApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
