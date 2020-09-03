package com.htjc.iot.protocol.version;

/**
 * @author ksssss
 * @Date: 20-9-3 09:32
 * @Description:
 */
public enum Version {
    DEFAULT((byte) 1);

    public final byte version;

    Version(byte version) {
        this.version = version;
    }
}
