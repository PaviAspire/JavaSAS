package com.application.apachecamel.routes;

import com.application.apachecamel.dto.WeatherDto;
import com.application.apachecamel.service.WeatherDataProvider;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RestDsl extends RouteBuilder {

    @Autowired
    WeatherDataProvider weatherDataProvider;

    @Override
    public void configure() throws Exception {
       rest().consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
               .get("/restdsl/weather/{city}").outType(WeatherDto.class).to("direct:getWeatherDetails")
               .post("/restdsl/weather").type(WeatherDto.class).to("direct:saveWeatherDetails");
       from("direct:getWeatherDetails").process(this::processWeatherData);
       from("direct:saveWeatherDetails").process(this::saveWeatherData);

    }
    private void saveWeatherData(Exchange exchange){
        WeatherDto weatherDto=exchange.getMessage().getBody(WeatherDto.class);
        weatherDataProvider.saveWeatherData(weatherDto);
    }

    public void processWeatherData(Exchange exchange){


        String city=exchange.getMessage().getHeader("city", String.class);
        Message message=new DefaultMessage(exchange.getContext());
        message.setBody(weatherDataProvider.getWeatherDetails(city));
        exchange.setMessage(message);


    }
}