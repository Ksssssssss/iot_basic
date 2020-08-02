package com.hoolai.im.protocol.request;

import lombok.Data;
import com.hoolai.im.protocol.Packet;

import static com.hoolai.im.protocol.command.Command.LOGOUT_REQUEST;

@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST.command;
    }
}
