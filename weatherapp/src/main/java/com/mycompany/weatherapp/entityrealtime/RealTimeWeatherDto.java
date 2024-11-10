package com.mycompany.weatherapp.entityrealtime;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RealTimeWeatherDto {
    private String city;
    private String weather;
    private String temperature;
    private LocalDateTime timestamp; // This will be used for transfer, and later converted when saving
}
