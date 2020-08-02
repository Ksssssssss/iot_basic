package com.hoolai.im.serialize;

import com.hoolai.im.serialize.impl.JSONSerializer;

public enum SerializerMode {
    /**
     * json 序列化
     */
    JSON((byte) 1, new JSONSerializer());

    public final Serializer serializer;
    public final byte mode;

    SerializerMode(byte mode, Serializer serializer) {
        this.mode = mode;
        this.serializer = serializer;
    }
}
