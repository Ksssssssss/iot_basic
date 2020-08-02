package com.hoolai.im.protocol.request;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 16:28
 */

@Data
public class HistoryRecodeRequestPacket extends Packet {
    private String toUserId;
    private Long lastMsgTime;

    @Override
    public Byte getCommand() {
        return Command.HISTORY_RECODE_REQUEST.command;
    }
}
