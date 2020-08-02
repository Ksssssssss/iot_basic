package com.hoolai.im.protocol.request;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

@Data
public class FriendListRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.FRIEND_LIST_REQUEST.command;
    }
}
