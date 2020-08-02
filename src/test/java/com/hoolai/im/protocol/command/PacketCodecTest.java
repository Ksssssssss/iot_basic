package com.hoolai.im.protocol.command;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Random;

public class PacketCodecTest {
    @Test
    public void encode() {
        String s  = new String("bottle_key|001");
        String[] split = s.split("\\|");
        String s1 = JSON.toJSONString(split);
        System.out.println(s1);
        Random random = new Random();

        System.out.println((int)(System.currentTimeMillis()/1000 - (System.currentTimeMillis()-100)/1000));
    }
}
