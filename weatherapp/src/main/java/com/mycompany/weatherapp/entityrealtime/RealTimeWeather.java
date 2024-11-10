package com.mycompany.weatherapp.entityrealtime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "weather_report")
public class RealTimeWeather {

    @Id
    private String id;  // MongoDB generates the ID automatically

    private String city;
    private String weather;
    private String temperature;

    private LocalDateTime timestamp;  // Use LocalDateTime for consistency across the application

    // Default constructor
    public RealTimeWeather() {}

    // Constructor with all fields
    public RealTimeWeather(String city, String weather, String temperature, LocalDateTime timestamp) {
        this.city = city;
        this.weather = weather;
        this.temperature = temperature;
        this.timestamp = timestamp;  // Store LocalDateTime directly
    }

    // Setters and Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Convert LocalDateTime to Date before saving to MongoDB
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Utility method to convert LocalDateTime to Date when needed (e.g., before saving to MongoDB)
    public Date getTimestampAsDate() {
        return Date.from(this.timestamp.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }
}
