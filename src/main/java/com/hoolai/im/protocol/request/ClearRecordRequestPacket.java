package com.hoolai.im.protocol.request;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

@Data
public class ClearRecordRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.CLEAR_RECODE_REQUEST.command;
    }
}
