package com.rabbitmqdemo.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue GetQueue(){
        return new Queue("hello");
    }
}
