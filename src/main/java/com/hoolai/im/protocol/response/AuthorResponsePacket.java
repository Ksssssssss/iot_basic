package com.hoolai.im.protocol.response;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

@Data
public class AuthorResponsePacket extends Packet {
    private boolean success;

    @Override
    public Byte getCommand() {

        return Command.AUTHOR_RESPONSE.command;
    }
}
