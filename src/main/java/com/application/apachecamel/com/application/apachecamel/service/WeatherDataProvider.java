package com.application.apachecamel.service;

import com.application.apachecamel.dto.WeatherDto;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherDataProvider {

    private static Map<String,WeatherDto> weatherData=new HashMap<>();

    public WeatherDataProvider(){
        WeatherDto weatherDto=WeatherDto.builder().city("london").temp("40").climate("Hot").registeredTime(new Date()).build();
        WeatherDto weatherDto1=WeatherDto.builder().city("san-fransisco").temp("10").climate("cool").registeredTime(new Date()).build();
        weatherData.put("LONDON",weatherDto);
        weatherData.put("SANFRANSISCO",weatherDto1);
    }
 public WeatherDto getWeatherDetails(String city){
        if(city== null || city.isEmpty() || city.equals("Test")){
            System.out.println("Test");
            throw new NullPointerException("City cannot be null");
        }
       return weatherData.get(city.toUpperCase());

 }

    public void saveWeatherData(WeatherDto weatherDto) {
        weatherDto.setRegisteredTime(new Date());
        weatherData.put(weatherDto.getCity().toUpperCase(),weatherDto);
    }
}
