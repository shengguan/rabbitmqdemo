package com.rabbitmqdemo.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author jingongmei
 * @ClassName: RedisConfig
 * @Description: redis 配置类
 * @date 2018年4月23日 下午3:05:40
 */
@Configuration
public class RedisConfig {

    // redisYmlConfig
    @Autowired
    @Qualifier("redisYmlConfig")
    private RedisYmlConfig redisYmlConfig;

    // redisPoolConfig
    @Autowired
    @Qualifier("redisPoolConfig")
    private RedisPoolConfig redisPoolConfig;

    // redisPool
    @Bean(name = "redisPool")
    @Qualifier("redisPool")
    public JedisPoolConfig redisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisPoolConfig.getMaxActive());
        poolConfig.setMaxIdle(redisPoolConfig.getMaxIdle());
        poolConfig.setTestOnBorrow(redisPoolConfig.isTestOnBorrow());
        return poolConfig;
    }

    // factory
    @Bean(name = "factory")
    @Qualifier("factory")
    public JedisConnectionFactory factory(@Qualifier("redisPool") JedisPoolConfig redisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory(redisPoolConfig);
        factory.setPort(redisYmlConfig.getPort());
        factory.setHostName(redisYmlConfig.getHost());
        return factory;
    }

    @Bean(name = "jedisWrapper")
    @Qualifier("jedisWrapper")
    public JedisWrapper jedisWrapper(@Qualifier("factory") JedisConnectionFactory redisFactory) {
        JedisWrapper jedisWrapper = new JedisWrapper(redisFactory);
        return jedisWrapper;
    }


}
