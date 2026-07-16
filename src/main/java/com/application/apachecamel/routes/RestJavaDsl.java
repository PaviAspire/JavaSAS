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

@Component
public class RestJavaDsl extends RouteBuilder {

    @Autowired
    WeatherDataProvider weatherDataProvider;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").contextPath("/camel");
      from("rest:get:javadsl/weather/{city}?produces=application/json").outputType(WeatherDto.class).process(this::processWeatherData);
    }

    public void processWeatherData(Exchange exchange){


        String city=exchange.getMessage().getHeader("city", String.class);
        Message message=new DefaultMessage(exchange.getContext());
        message.setBody(weatherDataProvider.getWeatherDetails(city));
        exchange.setMessage(message);


    }
}
