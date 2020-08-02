package com.hoolai.im.protocol.request;

import com.hoolai.im.protocol.Packet;
import com.hoolai.im.protocol.command.Command;
import lombok.Data;

@Data
public class RegisterRequestPacket extends Packet {
    private String encryptedData;
    private String iv;
    private String code;
    private String nick;
    /**
     * 0男、1女、2未知
     */
    private byte gender;
    private int age;

    @Override
    public Byte getCommand() {

        return Command.REGISTER_REQUEST.command;
    }
}
