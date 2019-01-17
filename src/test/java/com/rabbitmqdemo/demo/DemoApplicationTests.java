package com.rabbitmqdemo.demo;

import com.rabbitmqdemo.demo.rabbitmq.InsuranceProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

//	@Autowired
//	AmqpTemplate amqpTemplate;

	@Autowired
	InsuranceProducer insuranceProducer;

	@Test
	public void send(){
		String context = "此消息在，默认的交换机模式队列下，有 helloReceiver 可以收到";

		String routeKey = "hello";

		context = "routeKey:" + routeKey + ",context:" + context;

		System.out.println("sendHelloTest : " + context);
//
//		this.amqpTemplate.convertAndSend(routeKey, context);


		insuranceProducer.send(context);
	}

}
