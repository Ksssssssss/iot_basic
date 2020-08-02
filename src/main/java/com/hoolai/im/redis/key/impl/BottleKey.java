package com.hoolai.im.redis.key.impl;

import com.hoolai.im.redis.key.BaseKey;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-23 16:03
 */

public class BottleKey extends BaseKey {

    public static final BottleKey INSTANCE = new BottleKey();
    private final String key = "bottle_key";

    @Override
    public int expireTime() {
        return 60 * 10;
    }

    @Override
    public String prefix() {
        return key;
    }

    @Override
    public String produceKey(String openId) {
        return key+delimiter+openId;
    }

    public String decodeKey(String key) {
        return key.split(delimiter)[1];
    }
}
