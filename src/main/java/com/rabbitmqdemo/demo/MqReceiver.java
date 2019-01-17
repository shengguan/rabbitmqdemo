package com.rabbitmqdemo.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class MqReceiver {

    @RabbitHandler
    public void pocess(String hello){
        System.out.println("接受到的消息   RECEIVER DATA : "+hello);
    }
}
