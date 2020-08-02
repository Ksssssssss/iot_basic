package com.hoolai.im.serialize;

import com.hoolai.im.serialize.impl.JSONSerializer;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerMode();

    /**
     * java 对象转换成二进制
     */
    String serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

    <T> T deserialize(Class<T> clazz, String json);

}
