package com.hoolai.im.protocol.request;

import com.hoolai.im.entiy.Recode;
import com.hoolai.im.entiy.User;
import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 09:58
 */

@Data
public class ReceiveBottleResponsePacket extends Packet {

    private User user;
    private Recode recode;
    private boolean success;

    @Override
    public Byte getCommand() {
        return Command.RECEIVE_BOTTLE_RESPONSE.command;
    }
}
