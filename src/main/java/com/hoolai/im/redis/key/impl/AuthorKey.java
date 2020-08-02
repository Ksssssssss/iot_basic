package com.hoolai.im.redis.key.impl;

import com.hoolai.im.redis.key.Key;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 20:37
 */

public class AuthorKey implements Key {
    public static final AuthorKey INSTANCE = new AuthorKey();
    private final String prefix = "author";

    @Override
    public String prefix() {
        return prefix;
    }
}
