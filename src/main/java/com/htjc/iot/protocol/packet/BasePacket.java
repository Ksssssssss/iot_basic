package com.htjc.iot.protocol.packet;

import com.htjc.iot.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ksssss
 * @Date: 20-9-2 23:59
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePacket implements Packet {
    protected Header header;
    protected Body body;

    @Override
    public Header header() {
        return header;
    }

    @Override
    public Body body() {
        return body;
    }

    @Override
    public short checkNum() {
        return 0;
    }

    public boolean doCheckNum(short checkNum) {
        return checkNum() == checkNum;
    }
}
