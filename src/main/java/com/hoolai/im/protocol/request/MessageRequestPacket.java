package com.hoolai.im.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.hoolai.im.protocol.Packet;

import static com.hoolai.im.protocol.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String message;
    private String bottleMessage;
    private long bottleTime;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST.command;
    }
}
