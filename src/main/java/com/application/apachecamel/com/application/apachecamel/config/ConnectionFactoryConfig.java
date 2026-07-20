package com.application.apachecamel.config;


import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConnectionFactoryConfig {

    @Bean
    public ConnectionFactory rabbitConnectionFactory(){
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setPort(5672);
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
}
