package com.hoolai.im.protocol.response;

import com.hoolai.im.entiy.Recode;
import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 17:42
 */

@Data
public class HistoryRecodeResponsePacket extends Packet {

    private List<Recode> recodes;
    private boolean isEmpty;

    @Override
    public Byte getCommand() {
        return Command.HISTORY_RECODE_RESPONSE.command;
    }
}
