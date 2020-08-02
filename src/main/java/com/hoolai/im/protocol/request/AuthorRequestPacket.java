package com.hoolai.im.protocol.request;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

@Data
public class AuthorRequestPacket extends Packet {
    private String code;

    @Override
    public Byte getCommand() {

        return Command.AUTHOR_REQUEST.command;
    }
}
