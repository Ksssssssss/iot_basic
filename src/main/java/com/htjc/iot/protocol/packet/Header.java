package com.htjc.iot.protocol.packet;

import com.htjc.iot.protocol.command.Command;
import sun.misc.CRC16;

/**
 * @author ksssss
 * @date 20-9-2
 */
public interface Header {
    byte FRAME_HEADER = 0x7E;
    byte FRAME_TAIL = 0x7E;
    int TAIL_LEN = 3;

    /**
     * 设备号
     *
     * @return
     */
    Long id();

    /**
     * 协议版本
     *
     * @return
     */
    Byte version();

    /**
     * 状态码
     *
     * @return
     */
    Byte code();

    /**
     * 环境-数据类型-请求类型
     *
     * @return
     */
    Byte type();

    /**
     * 命令号
     *
     * @return
     */
    Command command();

    /**
     * 双方标识
     *
     * @return
     */
    int unid();

    /**
     * 校验码
     *
     * @return
     */
    static Short doCheckNum() {
        return null;
    }

}
