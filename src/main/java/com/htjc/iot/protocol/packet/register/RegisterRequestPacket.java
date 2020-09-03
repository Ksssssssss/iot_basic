package com.htjc.iot.protocol.packet.register;

import com.htjc.iot.protocol.Packet;
import com.htjc.iot.protocol.packet.BasePacket;
import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.Header;
import lombok.NoArgsConstructor;

/**
 * @author ksssss
 * @Date: 20-9-2 23:58
 * @Description:
 */

@NoArgsConstructor
public class RegisterRequestPacket extends BasePacket {

    public RegisterRequestPacket(Header header, Body body) {
        super(header, body);
    }
}
