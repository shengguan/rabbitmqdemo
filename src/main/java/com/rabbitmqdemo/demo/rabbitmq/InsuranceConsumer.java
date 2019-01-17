package com.rabbitmqdemo.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 消费者
 *
 * @author : w_g
 * @description :  TODO(这里用一句话描述这个类的作用)
 * @date : 2018/9/28 16:02
 */
@Configuration
@RabbitListener(queues = RabbitMqConfig.YWY_INSURANCE_QUEUE_NAME, containerFactory = "insuranceFactory")
public class InsuranceConsumer {

//    @Autowired
//    private InsuranceService insuranceService;

    @RabbitHandler
    public void save(Message message, String data, Channel channel) throws IOException {
        try {
            // 处理 消息
//            insuranceService.save(data);
            System.out.println("data = " + data);
            // ack标记已经消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 重新放入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

}
