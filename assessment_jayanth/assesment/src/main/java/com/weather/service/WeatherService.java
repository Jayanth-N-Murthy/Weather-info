package com.weather.service;

import com.weather.model.WeatherData;
import java.time.LocalDate;

public interface WeatherService {
    WeatherData getWeatherData(String pincode, LocalDate date);
} 