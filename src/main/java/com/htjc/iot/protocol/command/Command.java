package com.htjc.iot.protocol.command;

import com.htjc.iot.protocol.Packet;
import com.htjc.iot.protocol.packet.login.LoginResponsePacket;
import com.htjc.iot.protocol.packet.register.RegisterResponsePacket;
import com.htjc.iot.protocol.packet.login.LoginRequestPacket;
import com.htjc.iot.protocol.packet.register.RegisterRequestPacket;

public enum Command {

    /**
     * 1-10注册登陆登出
     */
    REGISTRY_REQUEST((short) 1, RegisterRequestPacket.class),
    REGISTRY_RESPONSE((short) 2, RegisterResponsePacket.class),
    LOGIN_RESPONSE((short) 4, LoginResponsePacket.class),
    LOGIN_REQUEST((short) 5, LoginRequestPacket.class);

    public final Short command;
    public final Class<? extends Packet> packet;

    Command(Short command, Class<? extends Packet> packet) {
        this.command = command;
        this.packet = packet;
    }
}
