package com.htjc.iot.codec;

import com.htjc.iot.protocol.Packet;
import com.htjc.iot.protocol.command.Command;
import com.htjc.iot.protocol.packet.BasePacket;
import com.htjc.iot.protocol.packet.Header;
import com.htjc.iot.protocol.packet.AttrType;
import com.htjc.iot.serialize.Serializer;
import com.htjc.iot.serialize.impl.KryoSerializer;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PacketCodec {

    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Short, Class<? extends Packet>> packetTypeMap;
    private final Serializer serializer;

    private PacketCodec() {
        packetTypeMap = new HashMap<>();

        for (Command value : Command.values()) {
            packetTypeMap.put(value.command, value.packet);
        }
        serializer = new KryoSerializer();
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = serializer.serialize(packet);
        byteBuf.writeByte(Header.FRAME_HEADER);
        Header header = packet.header();
        byteBuf.writeLong(header.id());
        byteBuf.writeByte(header.version());
        byteBuf.writeByte(header.code());
        byteBuf.writeByte(header.type());
        byteBuf.writeShort(header.command().command);
        byteBuf.writeInt(header.unid());
        byteBuf.writeShort(bytes.length);
        byteBuf.writeBytes(bytes);
        byteBuf.writeShort(packet.checkNum());
        byteBuf.writeByte(Header.FRAME_TAIL);
    }


    public Packet decode(ByteBuf byteBuf) {
        Packet packet = null;

        byte frameHeader = byteBuf.readByte();
        if (Header.FRAME_HEADER != frameHeader) {
            return packet;
        }

        //todo check
        long id = byteBuf.readLong();
        byte version = byteBuf.readByte();
        byte code = byteBuf.readByte();

        byte type = byteBuf.readByte();
        int reqType = AttrType.calculateReqType(type);
        int dataType = AttrType.calculateDataType(type);
        int env = AttrType.calculateEnv(type);


        short command = byteBuf.readShort();
        byteBuf.skipBytes(4);

        int len = byteBuf.readShort();
        byte[] bytes = new byte[len];

        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);

        if (requestType != null && serializer != null) {
            packet = serializer.deserialize(requestType, bytes);

            if (!byteBuf.isReadable(Header.TAIL_LEN)) {
                log.info("packet is lose,packet: {}", packet);
                return null;
            }
            final short checkNum = byteBuf.readShort();
            final boolean flag = ((BasePacket) packet).doCheckNum(checkNum);

            if (!flag) {
                log.info("checkNum is not match");
                return null;
            }

            if (byteBuf.readByte() != Header.FRAME_TAIL) {
                return null;
            }
        }

        return packet;
    }


    private Class<? extends Packet> getRequestType(short command) {

        return packetTypeMap.get(command);
    }
}
