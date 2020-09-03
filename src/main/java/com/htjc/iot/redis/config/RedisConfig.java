package com.htjc.iot.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-03-30 16:08
 */

@Configuration
@Data
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String host;
    private int port;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private String keys;
    private long timeOut;
    private final String delimiter = ",";

    public String[] keys() {
        if (StringUtils.isEmpty(keys)) {
            return null;
        }
        return StringUtils.split(keys, delimiter);
    }

}
