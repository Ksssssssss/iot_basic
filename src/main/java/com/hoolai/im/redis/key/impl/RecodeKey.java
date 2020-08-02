package com.hoolai.im.redis.key.impl;

import com.hoolai.im.redis.key.BaseKey;
import com.hoolai.im.redis.key.Key;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-04-23 20:13
 */

public class RecodeKey extends BaseKey {

    public static final RecodeKey INSTANCE = new RecodeKey();
    private final String KEY_PREFIX = "recode";

    @Override
    public String prefix() {
        return KEY_PREFIX;
    }

    @Override
    public String produceKey(String openId) {
        return KEY_PREFIX + delimiter + openId;
    }

    public String produceKey(String openId, String toUserId) {
        String key = openId.compareTo(toUserId) > 0 ? openId : toUserId;
        return KEY_PREFIX + delimiter + key;
    }

    @Override
    public int expireTime() {
        return 0;
    }
}
