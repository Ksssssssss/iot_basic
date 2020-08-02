package com.hoolai.im.protocol.response;

import lombok.Data;
import com.hoolai.im.protocol.Packet;

import static com.hoolai.im.protocol.command.Command.LOGOUT_RESPONSE;

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE.command;
    }
}
