package com.hoolai.im.redis.key.impl;

import com.hoolai.im.redis.key.BaseKey;
import com.hoolai.im.redis.key.Key;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 20:37
 */

public class UserKey implements Key {
    public static final UserKey INSTANCE = new UserKey();
    private final String prefix = "user";

    @Override
    public String prefix() {
        return prefix;
    }
}
