package com.hoolai.im.protocol.response;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

/**
 *
 *@description: 
 *@author: Ksssss(chenlin@hoolai.com)
 *@time: 2020-07-25 15:04
 * 
 */

@Data
public class ClearRecordResponsePacket extends Packet {
    private boolean success;
    @Override
    public Byte getCommand() {
        return Command.CLEAR_RECODE_REQUEST.command;
    }
}
