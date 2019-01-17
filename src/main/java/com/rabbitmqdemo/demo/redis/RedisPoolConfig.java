package com.rabbitmqdemo.demo.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jingongmei
 * @Description: redisPool 配置类
 * @date 2018年4月23日 下午3:05:40
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.pool")
public class RedisPoolConfig {
    private int maxActive;          // jedis实例最大数量(控制一个pool可分配多少个jedis实例)
    private int maxIdle;            // 控制一个pool最多有多少个状态为idle的jedis实例
    private boolean testOnBorrow;

    public RedisPoolConfig() {
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

}
