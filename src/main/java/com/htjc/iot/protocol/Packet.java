package com.htjc.iot.protocol;

import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.Header;

/**
 * @author ksssss
 * @date 20-9-2
 */
public interface Packet {

    /**
     * 协议头
     */
    Header header();

    /**
     * 协议体
     */
    Body body();

    short checkNum();
}
