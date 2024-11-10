package com.mycompany.weatherapp.entityhistory;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class WeatherHistoryDto {

    @NotNull(message = "City cannot be null")
    @Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
    private String city;

    @NotNull(message = "Weather description cannot be null")
    @Size(min = 3, max = 100, message = "Weather description must be between 3 and 100 characters")
    private String weather;

    @NotNull(message = "Temperature cannot be null")
    private String temperature;

    @NotNull(message = "Timestamp cannot be null")
    private LocalDateTime timestamp;
}
