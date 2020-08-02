package com.hoolai.im.protocol.command;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.request.*;
import com.hoolai.im.protocol.response.*;

public enum Command {

//    //1-2心跳处理
//    HEARTBEAT_REQUEST((byte) 1, HeartBeatRequestPacket.class),
//    HEARTBEAT_RESPONSE((byte) 2, HeartBeatResponsePacket.class),

    //1-10登陆登出

    AUTHOR_REQUEST((byte) 1, AuthorRequestPacket.class),
    AUTHOR_RESPONSE((byte) 2, AuthorResponsePacket.class),
    REGISTER_REQUEST((byte) 3, RegisterRequestPacket.class),
    REGISTER_RESPONSE((byte) 4, RegisterResponsePacket.class),
    LOGIN_REQUEST((byte) 5, LoginRequestPacket.class),
    LOGIN_RESPONSE((byte) 6, LoginResponsePacket.class),
    LOGOUT_REQUEST((byte) 7, LogoutRequestPacket.class),
    LOGOUT_RESPONSE((byte) 8, LogoutResponsePacket.class),

    //11-30瓶子处理
    SEND_BOTTLE_REQUEST((byte) 11, SendBottleRequestPacket.class),
    SEND_BOTTLE_RESPONSE((byte) 12, SendBottleResponsePacket.class),
    RECEIVE_BOTTLE_REQUEST((byte) 13, ReceiveBottleRequestPacket.class),
    RECEIVE_BOTTLE_RESPONSE((byte) 14, ReceiveBottleResponsePacket.class),
    REPLY_BOTTLE_MESSAGE_RESPONSE((byte) 15, ReplyBottleMessageResponsePacket.class),
    //31-50消息处理
    MESSAGE_REQUEST((byte) 31, MessageRequestPacket.class),
    MESSAGE_RESPONSE((byte) 32, MessageResponsePacket.class),
    HISTORY_RECODE_REQUEST((byte) 33, HistoryRecodeRequestPacket.class),
    HISTORY_RECODE_RESPONSE((byte) 34, HistoryRecodeResponsePacket.class),
    // 清空记录
    CLEAR_RECODE_REQUEST((byte) 35, ClearRecordRequestPacket.class),
    CLEAR_RECODE_RESPONSE((byte) 36, ClearRecordResponsePacket.class),

    //51-60 好友处理
    FRIEND_LIST_REQUEST((byte) 51, FriendListRequestPacket.class),
    FRIEND_LIST_RESPONSE((byte) 52, FriendListResponsePacket.class);

    public final byte command;
    public final Class<? extends Packet> packet;

    Command(byte command, Class<? extends Packet> packet) {
        this.command = command;
        this.packet = packet;
    }
}
