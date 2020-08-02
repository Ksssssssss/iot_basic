package com.hoolai.im.redis.key.impl;

import com.hoolai.im.redis.key.BaseKey;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-04-24 11:36
 */

public class FriendListKey extends BaseKey {

    public static final FriendListKey INSTANCE = new FriendListKey();

    private final String DEFAULT_KEY_PREFIX = "friend_list";

    @Override
    public String prefix() {
        return DEFAULT_KEY_PREFIX;
    }

    @Override
    public String produceKey(String openId) {
        return DEFAULT_KEY_PREFIX + delimiter + openId;
    }

    @Override
    public int expireTime() {
        return 0;
    }
}
