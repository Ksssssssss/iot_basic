package com.htjc.iot.protocol.packet;

/**
 * @author ksssss
 * @Date: 20-9-2 22:36
 * @Description:
 */

public class AttrType {
    private static final int DATA_LEN = 4;
    private static final int REQ_LEN = 3;

    public static int calculateDataType(Byte type) {
        int dataType = type & ((2 << DATA_LEN) - 1);
        return dataType;
    }

    public static int calculateReqType(Byte type) {
        int reqType = (type >> DATA_LEN) & ((2 << REQ_LEN) - 1);
        return reqType;
    }

    public static int calculateEnv(Byte type) {
        return type >> (DATA_LEN + REQ_LEN);
    }
}
