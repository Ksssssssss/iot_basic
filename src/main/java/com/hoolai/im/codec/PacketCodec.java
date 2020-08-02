package com.hoolai.im.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hoolai.im.protocol.Packet;
import com.hoolai.im.serialize.Serializer;
import com.hoolai.im.serialize.SerializerMode;
import com.hoolai.im.protocol.command.Command;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;
import java.util.Map;

import static com.hoolai.im.protocol.command.Command.*;

public class PacketCodec {

    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;


    private PacketCodec() {
        packetTypeMap = new HashMap<>();
        for (Command value : values()) {
            packetTypeMap.put(value.command, value.packet);
        }

        serializerMap = new HashMap<>();
        for (SerializerMode value : SerializerMode.values()) {
            serializerMap.put(value.mode, value.serializer);
        }
    }

    public TextWebSocketFrame encode(Packet packet) {
        // 1. 序列化 java 对象
        String msg = Serializer.DEFAULT.serialize(packet);
        return new TextWebSocketFrame(msg);
    }


    public Packet decode(JSONObject jsonMap) {
        // 跳过 magic number
        String magic = jsonMap.getString("magic");
        // 指令
        byte command = jsonMap.getByte("command");

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = Serializer.DEFAULT;

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, jsonMap.toJSONString());
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
