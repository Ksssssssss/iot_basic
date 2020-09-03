package com.htjc.iot.serialize;

import com.htjc.iot.serialize.impl.KryoSerializer;

public interface Serializer {

    Serializer DEFAULT = new KryoSerializer();


    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
