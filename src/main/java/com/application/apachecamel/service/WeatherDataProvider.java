package com.application.apachecamel.service;

import com.application.apachecamel.dto.WeatherDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherDataProvider {

    private static Map<String,WeatherDto> weatherData=new HashMap<>();

    public WeatherDataProvider(){
        WeatherDto weatherDto=WeatherDto.builder().city("london").temp("40").climate("Hot").registeredTime(LocalDateTime.now()).build();
        WeatherDto weatherDto1=WeatherDto.builder().city("san-fransisco").temp("10").climate("cool").registeredTime(LocalDateTime.now()).build();
        weatherData.put("LONDON",weatherDto);
        weatherData.put("SANFRANSISCO",weatherDto1);
    }
 public WeatherDto getWeatherDetails(String city){
       return weatherData.get(city.toUpperCase());

 }

    public void saveWeatherData(WeatherDto weatherDto) {
        weatherDto.setRegisteredTime(LocalDateTime.now());
        weatherData.put(weatherDto.getCity().toUpperCase(),weatherDto);
    }
}
