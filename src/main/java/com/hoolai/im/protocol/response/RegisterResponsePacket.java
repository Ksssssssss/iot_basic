package com.hoolai.im.protocol.response;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

@Data
public class RegisterResponsePacket extends Packet {

    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.REGISTER_RESPONSE.command;
    }
}
