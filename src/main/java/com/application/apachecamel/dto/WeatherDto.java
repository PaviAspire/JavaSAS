package com.application.apachecamel.dto;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherDto {
    private static int counter=1;
 private int id=counter++;
 private String city;
 private String temp;
 private String climate;
 private LocalDateTime registeredTime;
}
