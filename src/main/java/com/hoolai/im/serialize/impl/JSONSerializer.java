package com.hoolai.im.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.hoolai.im.serialize.Serializer;
import com.hoolai.im.serialize.SerializerMode;

public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerMode() {
        return SerializerMode.JSON.mode;
    }

    @Override
    public String serialize(Object object) {

        return JSON.toJSONString(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, String json) {
        return JSON.parseObject(json, clazz);
    }
}
