package com.hoolai.im.redis.key;

import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-04-23 20:10
 */

@Data
public abstract class BaseKey implements Key {

    public BaseKey() {

    }

    public abstract int expireTime();

    @Override
    public abstract String prefix();

    public abstract String produceKey(String openId);
}
