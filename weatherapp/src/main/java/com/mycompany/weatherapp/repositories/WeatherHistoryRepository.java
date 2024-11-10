package com.mycompany.weatherapp.repositories;

import com.mycompany.weatherapp.entityhistory.WeatherHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WeatherHistoryRepository extends JpaRepository<WeatherHistory, Long> {

    // Example of custom query to find WeatherHistory by city
    List<WeatherHistory> findByCity(String city);

    // Example of custom query to find WeatherHistory by weather
    List<WeatherHistory> findByWeather(String weather);
}
