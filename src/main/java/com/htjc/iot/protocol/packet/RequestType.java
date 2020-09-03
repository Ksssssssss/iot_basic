package com.htjc.iot.protocol.packet;

/**
 * @author ksssss
 * @Date: 20-9-3 00:22
 * @Description:
 */
public enum RequestType {
    QUERY((byte) 1),
    PUB_ACK((byte) 5);
    public final byte type;

    RequestType(byte type) {
        this.type = type;
    }
}
