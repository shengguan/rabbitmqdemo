package com.rabbitmqdemo.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    // 保险
    public static final String YWY_INSURANCE_EXCHANGE = "ywy_insurance-exchange";
    public static final String YWY_INSURANCE_ROUTING_KEY = "ywy_insurance-routingKey";
    public static final String YWY_INSURANCE_QUEUE_NAME = "ywy_insurance";

    /**
     * 消费者数量，默认10
     */
    @Value("${default.concurrent.count}")
    public int defaultConCurrent;

    /**
     * 每个消费者获取最大投递数量 默认50
     */
    @Value("${default.prefetch.count}")
    public int defaultPrefetchCount;

    @Value("${spring.rabbitmq.addresses_new}")
    private String address;

    @Value("${spring.rabbitmq.username_new}")
    private String username;

    @Value("${spring.rabbitmq.password_new}")
    private String password;

    @Value("${spring.rabbitmq.port_new}")
    private Integer port;

    /**
     * 配置连接信息
     *
     * @return
     */
    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setAddresses(address);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setPort(port);
        factory.setVirtualHost("/");
        // 必要设置
        factory.setPublisherConfirms(true);
        factory.setConnectionTimeout(1000);

        return factory;
    }

    @Bean(name = "insuranceTemplate")
    public RabbitTemplate insuranceTemplate(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    /**
     * insuranceFactory,消费者
     *
     * @param configurer
     * @param connectionFactory
     * @return
     */
    @Bean(name = "insuranceFactory")
    public SimpleRabbitListenerContainerFactory insuranceFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                 @Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(defaultPrefetchCount);
        factory.setConcurrentConsumers(defaultConCurrent);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    /**
     * 构建队列,名称,是否持久化之类
     *
     * @return
     */
    @Bean
    public Queue insuranceQueue() {
        return new Queue(YWY_INSURANCE_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange insuranceExchange() {
        return new DirectExchange(YWY_INSURANCE_EXCHANGE);
    }

    @Bean
    public Binding insuranceBinding() {
        return BindingBuilder.bind(insuranceQueue()).to(insuranceExchange()).with(YWY_INSURANCE_ROUTING_KEY);
    }

}
