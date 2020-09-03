package com.htjc.iot.protocol.packet.login;

import com.htjc.iot.protocol.packet.BasePacket;
import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.Header;
import lombok.NoArgsConstructor;

/**
 * @author ksssss
 * @Date: 20-9-2 15:26
 * @Description:
 */

@NoArgsConstructor
public class LoginResponsePacket extends BasePacket {

    public LoginResponsePacket(Header header, Body body) {
        super(header, body);
    }
}
