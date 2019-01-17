package com.rabbitmqdemo.demo.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jingongmei
 * @Description: redisYml 配置类
 * @date 2018年4月23日 下午3:05:40
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisYmlConfig {

    private String host;        // 地址
    private int port;            // 端口

    public RedisYmlConfig() {

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
