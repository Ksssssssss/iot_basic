package com.hoolai.im.protocol.response;

import com.hoolai.im.entiy.Friend;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.hoolai.im.protocol.Packet;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.hoolai.im.protocol.command.Command.LOGIN_RESPONSE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String openId;
    private String nick;

    private String reason;

    @Override
    public Byte getCommand() {

        return LOGIN_RESPONSE.command;
    }
}
