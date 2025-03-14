package com.weather.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "weather_data")
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private LocalDate date;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    private String description;
    private String icon;
} 