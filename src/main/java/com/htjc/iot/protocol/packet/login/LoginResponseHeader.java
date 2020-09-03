package com.htjc.iot.protocol.packet.login;

import com.htjc.iot.protocol.command.Command;
import com.htjc.iot.protocol.packet.Header;
import com.htjc.iot.protocol.packet.RequestType;
import com.htjc.iot.protocol.status.StatusCode;
import com.htjc.iot.protocol.version.Version;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author ksssss
 * @Date: 20-9-2 15:26
 * @Description:
 */

@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseHeader implements Header {
    private long id;

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Byte version() {
        return Version.DEFAULT.version;
    }

    @Override
    public Byte code() {
        return StatusCode.SUCCESS.code;
    }

    @Override
    public Byte type() {
        return RequestType.QUERY.type;
    }

    @Override
    public Command command() {
        return Command.LOGIN_RESPONSE;
    }

    @Override
    public int unid() {
        return 0;
    }
}
