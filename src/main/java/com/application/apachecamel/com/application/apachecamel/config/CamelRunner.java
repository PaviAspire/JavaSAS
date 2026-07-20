package com.application.apachecamel.config;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CamelRunner implements CommandLineRunner {

    @Autowired
    ProducerTemplate producerTemplate;

    @Override
    public void run(String... args) {
        String json = """
{
  "employee": {
    "name": "John",
    "salary": 70000
  }
}
""";
        /*producerTemplate.sendBodyAndHeader(
                "direct:start",
                "User is added",
                "amount",
                1500);*/
        producerTemplate.send("direct:circuitdemo",e->{
            e.getMessage().setBody(json);
            e.getMessage().setHeader("userID",1244);
        });
    }
}