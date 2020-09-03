package com.htjc.iot.protocol.packet.register;

import com.htjc.iot.protocol.command.Command;
import com.htjc.iot.protocol.packet.Header;
import com.htjc.iot.protocol.packet.RequestType;
import com.htjc.iot.protocol.status.StatusCode;
import com.htjc.iot.protocol.version.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ksssss
 * @Date: 20-9-2 23:44
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestHeader implements Header {
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
        return StatusCode.NONE.code;
    }

    @Override
    public Byte type() {
        return RequestType.PUB_ACK.type;
    }

    @Override
    public Command command() {
        return Command.REGISTRY_REQUEST;
    }

    @Override
    public int unid() {
        return 0;
    }
}
