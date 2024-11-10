package com.mycompany.weatherapp.repositories;

import com.mycompany.weatherapp.entityrealtime.RealTimeWeather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealTimeWeatherRepository extends MongoRepository<RealTimeWeather, String> {
    // Custom query to find real-time weather data by city
    List<RealTimeWeather> findByCity(String city);

    // Custom query to find real-time weather data by weather condition
    List<RealTimeWeather> findByWeather(String weather);
}
