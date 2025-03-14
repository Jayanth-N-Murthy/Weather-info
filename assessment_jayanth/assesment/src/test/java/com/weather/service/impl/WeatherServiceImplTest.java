package com.weather.service.impl;

import com.weather.model.Location;
import com.weather.model.WeatherData;
import com.weather.repository.LocationRepository;
import com.weather.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    private Location testLocation;
    private WeatherData testWeatherData;
    private String testPincode;
    private LocalDate testDate;

    @BeforeEach
    void setUp() {
        testPincode = "411014";
        testDate = LocalDate.of(2020, 10, 15);

        testLocation = new Location();
        testLocation.setId(1L);
        testLocation.setPincode(testPincode);
        testLocation.setLatitude(18.5204);
        testLocation.setLongitude(73.8567);

        testWeatherData = new WeatherData();
        testWeatherData.setId(1L);
        testWeatherData.setLocation(testLocation);
        testWeatherData.setDate(testDate);
        testWeatherData.setTemperature(25.0);
        testWeatherData.setHumidity(65.0);
        testWeatherData.setWindSpeed(5.0);
        testWeatherData.setDescription("Clear sky");
        testWeatherData.setIcon("01d");
    }

    @Test
    void getWeatherData_WhenDataExists_ReturnsExistingData() {
        when(locationRepository.findByPincode(testPincode)).thenReturn(Optional.of(testLocation));
        when(weatherDataRepository.findByLocationAndDate(testLocation, testDate))
                .thenReturn(Optional.of(testWeatherData));

        WeatherData result = weatherService.getWeatherData(testPincode, testDate);

        assertNotNull(result);
        assertEquals(testWeatherData.getId(), result.getId());
        assertEquals(testWeatherData.getTemperature(), result.getTemperature());
        assertEquals(testWeatherData.getHumidity(), result.getHumidity());
        assertEquals(testWeatherData.getWindSpeed(), result.getWindSpeed());
        assertEquals(testWeatherData.getDescription(), result.getDescription());
    }

    @Test
    void getWeatherData_WhenLocationExistsButWeatherDataDoesNot_FetchesNewData() {
        when(locationRepository.findByPincode(testPincode)).thenReturn(Optional.of(testLocation));
        when(weatherDataRepository.findByLocationAndDate(testLocation, testDate))
                .thenReturn(Optional.empty());
        when(weatherDataRepository.save(any(WeatherData.class))).thenReturn(testWeatherData);

        WeatherData result = weatherService.getWeatherData(testPincode, testDate);

        assertNotNull(result);
        assertEquals(testWeatherData.getId(), result.getId());
    }
} 