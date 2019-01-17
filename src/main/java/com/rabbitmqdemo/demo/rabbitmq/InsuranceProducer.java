package com.rabbitmqdemo.demo.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author : w_g
 * @description :  TODO(这里用一句话描述这个类的作用)
 * @date : 2018/9/28 15:40
 */
@Component("insuranceProducer")
public class InsuranceProducer {

    @Autowired
    @Qualifier("insuranceTemplate")
    private RabbitTemplate insuranceTemplate;

    /**
     * 将业务员投保信息放入队列
     *
     * @param data 投保信息
     * @return
     * @throws AmqpException
     */
    public boolean send(String data) throws AmqpException {
        insuranceTemplate.convertAndSend(RabbitMqConfig.YWY_INSURANCE_EXCHANGE, RabbitMqConfig.YWY_INSURANCE_ROUTING_KEY, data);
        return true;
    }

}
