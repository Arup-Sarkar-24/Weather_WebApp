package com.mycompany.weatherapp.controller;

import com.mycompany.weatherapp.entityrealtime.RealTimeWeather;
import com.mycompany.weatherapp.entityhistory.WeatherHistory;
import com.mycompany.weatherapp.repositories.RealTimeWeatherRepository;
import com.mycompany.weatherapp.repositories.WeatherHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.JSONObject;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class WeatherForecastApp {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private WeatherHistoryRepository weatherHistoryRepository;

    @Autowired
    private RealTimeWeatherRepository realTimeWeatherRepository;

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        String requestUrl = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("appid", apiKey)
                .toUriString();
        String response = sendGetRequest(requestUrl);

        if (!response.startsWith("Error")) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                String temperature = jsonObject.getJSONObject("main").getString("temp");

                // Display data on UI
                model.addAttribute("city", city);
                model.addAttribute("weather", weatherDescription);
                model.addAttribute("temperature", temperature);

                // Save to MongoDB (real-time data)
                RealTimeWeather realTimeData = new RealTimeWeather();
                realTimeData.setCity(city);
                realTimeData.setWeather(weatherDescription);
                realTimeData.setTemperature(temperature);
                realTimeData.setTimestamp(LocalDateTime.now());
                realTimeWeatherRepository.save(realTimeData);

// If needed:
                Date mongoTimestamp = realTimeData.getTimestampAsDate(); // This is the Date you can store in MongoDB


                // Save to PostgreSQL (historical data)
                WeatherHistory historicalData = new WeatherHistory();
                historicalData.setCity(city);
                historicalData.setWeather(weatherDescription);
                historicalData.setTemperature(temperature);
                historicalData.setTimestamp(LocalDateTime.now());  // No conversion needed here for LocalDateTime
                weatherHistoryRepository.save(historicalData);
            } catch (org.json.JSONException e) {
                model.addAttribute("error", "Error parsing weather data.");
            }
        } else {
            model.addAttribute("error", "Could not retrieve weather data");
        }

        return "weather";
    }

    private String sendGetRequest(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return "Error: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}

