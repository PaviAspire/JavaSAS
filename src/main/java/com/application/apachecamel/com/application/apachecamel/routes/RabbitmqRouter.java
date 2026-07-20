package com.application.apachecamel.routes;

import com.application.apachecamel.dto.WeatherDto;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.support.DefaultMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConditionalOnProperty(name = "app.enable.route",havingValue ="rmq")
public class RabbitmqRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
       from("rabbitmq:amq.direct?queue=weather&routingKey=weather&autoDelete=false")
                .unmarshal().json(JsonLibrary.Jackson, WeatherDto.class)
            //adding wiretap EIP
               .wireTap("direct:wireTap")
               .log(LoggingLevel.ERROR,"Content from rmq :${body}").process(this::enrichedDetails)
               .log(LoggingLevel.ERROR,"afterenriched:: ${body}")
               .marshal().json(JsonLibrary.Jackson, WeatherDto.class)
               .to("rabbitmq:amq.direct?queue=weather_reciever&routingKey=weather_rc&autoDelete=false");
    from("direct:wireTap").process(this::enrichedDetails)
             .to("rabbitmq:amq.direct?queue=audit_reciever&routingKey=audit_rc&autoDelete=false");


    }
    private void enrichedDetails(Exchange exchange){
        WeatherDto weatherDto=exchange.getMessage().getBody(WeatherDto.class);
        weatherDto.setRegisteredTime(new Date());

        Message m=new DefaultMessage(exchange);
        m.setBody(weatherDto);
        exchange.setMessage(m);
    }
}
