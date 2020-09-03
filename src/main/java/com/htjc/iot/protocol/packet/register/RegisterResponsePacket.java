package com.htjc.iot.protocol.packet.register;

import com.htjc.iot.protocol.packet.BasePacket;
import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.Header;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author ksssss
 * @Date: 20-9-3 10:19
 * @Description:
 */
@NoArgsConstructor
public class RegisterResponsePacket extends BasePacket {
    public RegisterResponsePacket(Header header, Body body) {
        super(header, body);
    }
}
