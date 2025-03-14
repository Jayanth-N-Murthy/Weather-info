package com.weather.service.impl;

import com.weather.model.Location;
import com.weather.model.WeatherData;
import com.weather.repository.LocationRepository;
import com.weather.repository.WeatherDataRepository;
import com.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final LocationRepository locationRepository;
    private final WeatherDataRepository weatherDataRepository;
    private final RestTemplate restTemplate;

    @Override
    public WeatherData getWeatherData(String pincode, LocalDate date) {
        // Check if we already have weather data for this pincode and date
        Location location = locationRepository.findByPincode(pincode)
                .orElseGet(() -> createNewLocation(pincode));

        return weatherDataRepository.findByLocationAndDate(location, date)
                .orElseGet(() -> fetchAndSaveWeatherData(location, date));
    }

    private Location createNewLocation(String pincode) {
        // Call Google Maps Geocoding API to get lat/long for pincode
        // This is a placeholder - you'll need to implement the actual API call
        Location location = new Location();
        location.setPincode(pincode);
        // Set lat/long from API response
        return locationRepository.save(location);
    }

    private WeatherData fetchAndSaveWeatherData(Location location, LocalDate date) {
        // Call OpenWeather API to get weather data
        // This is a placeholder - you'll need to implement the actual API call
        WeatherData weatherData = new WeatherData();
        weatherData.setLocation(location);
        weatherData.setDate(date);
        // Set weather data from API response
        return weatherDataRepository.save(weatherData);
    }
} 