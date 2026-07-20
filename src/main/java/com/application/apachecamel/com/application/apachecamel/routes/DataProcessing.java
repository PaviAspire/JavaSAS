package com.application.apachecamel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static java.lang.ProcessBuilder.Redirect.to;

@Component
@ConditionalOnProperty(name="app.enable.route",havingValue = "DP")
public class DataProcessing extends RouteBuilder {

    @Override
    public void configure() throws Exception {


// Simple Expression Language
        from("direct:start").
                choice().
                when(simple("${header.amount} > 1000"))
                .log("its greater value")
                .to("mock:greaterQueue")
                .otherwise()
                .to("mock:normal");
        from("direct:jsonpathQueue").
                choice().when().jsonpath("$.employee.salary > 6000")
                .log("its greater value")
                .to("mock:greaterQueue")
                .otherwise()
                .to("mock:normal");
        from("timer:req?period=500").
        circuitBreaker().resilience4jConfiguration().timeoutEnabled(true).timeoutDuration(1000).automaticTransitionFromOpenToHalfOpenEnabled(true)
                .end()
                .throwException(new NullPointerException())
                .onFallback()
                .transform().constant("Fall back error").end().log("req fallback :${body}");
    }
}
