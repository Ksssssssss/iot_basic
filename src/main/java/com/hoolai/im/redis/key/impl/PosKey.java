package com.hoolai.im.redis.key.impl;

import com.hoolai.im.redis.key.BaseKey;
import com.hoolai.im.redis.key.Key;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-04-23 20:13
 */

public class PosKey implements Key {

    private final String KEY_PREFIX = "pos";
    @Override
    public String prefix() {
        return KEY_PREFIX;
    }

}
