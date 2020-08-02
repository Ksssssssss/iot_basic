package com.hoolai.im.protocol.response;

import lombok.Data;
import com.hoolai.im.protocol.Packet;

import static com.hoolai.im.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private long msgTime;

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE.command;
    }
}
