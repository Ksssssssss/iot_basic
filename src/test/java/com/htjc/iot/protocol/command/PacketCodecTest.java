package com.htjc.iot.protocol.command;

import com.alibaba.fastjson.JSON;
import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.Header;
import com.htjc.iot.protocol.packet.register.RegisterRequestHeader;
import com.htjc.iot.protocol.packet.register.RegisterRequestPacket;
import com.htjc.iot.serialize.Serializer;
import com.htjc.iot.serialize.impl.KryoSerializer;
import org.junit.Test;

import java.util.Random;

public class PacketCodecTest {
    @Test
    public void encode() {
        String s = new String("bottle_key|001");
        String[] split = s.split("\\|");
        String s1 = JSON.toJSONString(split);
        System.out.println(s1);
        Random random = new Random();

        System.out.println((int) (System.currentTimeMillis() / 1000 - (System.currentTimeMillis() - 100) / 1000));
    }

    @Test
    public void KryoSerialize() {
        Serializer serializer = new KryoSerializer();
        Header header = new RegisterRequestHeader(100);
        RegisterRequestPacket registerRequestPacket = new RegisterRequestPacket(header, new Body());

        byte[] serialize = serializer.serialize(registerRequestPacket);
        RegisterRequestPacket requestPacket = serializer.deserialize(RegisterRequestPacket.class, serialize);
        Command command = requestPacket.getHeader().command();
        System.out.println(requestPacket.getHeader().id());
        System.out.println(command);
    }
}
