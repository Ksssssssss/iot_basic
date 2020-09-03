package com.htjc.iot.redis;

/**
 * @author ksssss
 * @Date: 20-9-3 15:08
 * @Description:
 */
public enum Key {
    DEVICE_KEY("device");

    public final String prefix;
    public String expireTime;
    public final String delimiter = "&";

    Key(String prefix) {
        this.prefix = prefix;
    }

    Key(String prefix, String expireTime) {
        this.prefix = prefix;
        this.expireTime = expireTime;
    }

    public final String produceKey(String key) {
        return prefix + delimiter + key;
    }
}
