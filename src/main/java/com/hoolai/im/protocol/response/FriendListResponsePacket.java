package com.hoolai.im.protocol.response;

import com.hoolai.im.entiy.Friend;
import com.hoolai.im.entiy.Recode;
import com.hoolai.im.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.hoolai.im.protocol.command.Command.FRIEND_LIST_RESPONSE;

@Data
public class FriendListResponsePacket extends Packet {

    private List<? extends Recode> friendList;

    @Override
    public Byte getCommand() {

        return FRIEND_LIST_RESPONSE.command;
    }
}
