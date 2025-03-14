package com.weather.controller;

import com.weather.model.WeatherData;
import com.weather.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Api(tags = "Weather API")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    @ApiOperation("Get weather data for a pincode and date")
    public ResponseEntity<WeatherData> getWeatherData(
            @RequestParam String pincode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(weatherService.getWeatherData(pincode, date));
    }
} 