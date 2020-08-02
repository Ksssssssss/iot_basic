//package com.hoolai.im.protocol.response;
//
//import lombok.Data;
//import com.hoolai.im.protocol.Packet;
//
//import java.util.List;
//
//import static com.hoolai.im.protocol.command.Command.CREATE_GROUP_RESPONSE;
//
//@Data
//public class CreateGroupResponsePacket extends Packet {
//    private boolean success;
//
//    private String groupId;
//
//    private List<String> userNameList;
//
//    @Override
//    public Byte getCommand() {
//
//        return CREATE_GROUP_RESPONSE.command;
//    }
//}
