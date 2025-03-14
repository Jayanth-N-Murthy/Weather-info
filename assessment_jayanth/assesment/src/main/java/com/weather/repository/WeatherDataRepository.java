package com.weather.repository;

import com.weather.model.Location;
import com.weather.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    Optional<WeatherData> findByLocationAndDate(Location location, LocalDate date);
} 