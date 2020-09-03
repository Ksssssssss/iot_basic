package com.htjc.iot.protocol.status;

/**
 * @author ksssss
 * @Date: 20-9-3 00:16
 * @Description:状态码
 */
public enum StatusCode {
    NONE((byte) 0x00),
    SUCCESS((byte) 0x02);

    public final byte code;

    StatusCode(byte code) {
        this.code = code;
    }
}
