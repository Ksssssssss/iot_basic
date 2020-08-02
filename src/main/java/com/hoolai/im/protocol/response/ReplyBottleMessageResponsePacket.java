package com.hoolai.im.protocol.response;

import com.hoolai.im.entiy.Recode;
import com.hoolai.im.protocol.Packet;
import lombok.Data;

import static com.hoolai.im.protocol.command.Command.*;

@Data
public class ReplyBottleMessageResponsePacket extends Packet {

    private Recode recode;

    private String nick;

    @Override
    public Byte getCommand() {

        return REPLY_BOTTLE_MESSAGE_RESPONSE.command;
    }
}
