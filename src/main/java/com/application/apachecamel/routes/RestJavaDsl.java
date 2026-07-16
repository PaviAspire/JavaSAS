package com.application.apachecamel.routes;

import com.application.apachecamel.dto.WeatherDto;
import com.application.apachecamel.service.WeatherDataProvider;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class RestJavaDsl extends RouteBuilder {

    @Autowired
    WeatherDataProvider weatherDataProvider;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").contextPath("/camel");
      from("rest:get:javadsl/weather/{city}?produces=application/json").outputType(WeatherDto.class).process(this::processWeatherData);
    //now  we need to save it to save it so refactoring code
    }

    public void processWeatherData(Exchange exchange){


        String city=exchange.getMessage().getHeader("city", String.class);
        Message message=new DefaultMessage(exchange.getContext());
        WeatherDto currentWeather=weatherDataProvider.getWeatherDetails(city);
        if(Objects.nonNull(currentWeather)) {
            message.setBody(currentWeather);
            exchange.setMessage(message);
        }else{
            exchange.getMessage().setHeader(HTTP_RESPONSE_CODE,NOT_FOUND);
        }
    }
}
