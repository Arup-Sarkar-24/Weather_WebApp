package com.mycompany.weatherapp.builder;

import com.mycompany.weatherapp.entityhistory.WeatherHistory;
import com.mycompany.weatherapp.entityhistory.WeatherHistoryDto;
import com.mycompany.weatherapp.entityrealtime.RealTimeWeather;
import com.mycompany.weatherapp.entityrealtime.RealTimeWeatherDto;

public class ObjectBuilder {

    // Method to convert WeatherHistoryDto to WeatherHistory entity
    public static WeatherHistory buildWeatherHistoryFromDto(WeatherHistoryDto weatherHistoryDto) {
        WeatherHistory weatherHistory = new WeatherHistory();
        weatherHistory.setCity(weatherHistoryDto.getCity());
        weatherHistory.setWeather(weatherHistoryDto.getWeather());
        weatherHistory.setTemperature(weatherHistoryDto.getTemperature());
        weatherHistory.setTimestamp(weatherHistoryDto.getTimestamp());
        return weatherHistory;
    }

    // Method to convert RealTimeWeatherDto to RealTimeWeather entity
    public static RealTimeWeather buildRealTimeWeatherFromDto(RealTimeWeatherDto realTimeWeatherDto) {
        RealTimeWeather realTimeWeather = new RealTimeWeather();
        realTimeWeather.setCity(realTimeWeatherDto.getCity());
        realTimeWeather.setWeather(realTimeWeatherDto.getWeather());
        realTimeWeather.setTemperature(realTimeWeatherDto.getTemperature()); // Fixed this line
        realTimeWeather.setTimestamp(realTimeWeatherDto.getTimestamp());
        return realTimeWeather;
    }
}
