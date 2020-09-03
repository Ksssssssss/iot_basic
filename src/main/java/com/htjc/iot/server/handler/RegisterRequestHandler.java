package com.htjc.iot.server.handler;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.Header;
import com.htjc.iot.protocol.packet.register.RegisterRequestPacket;
import com.htjc.iot.protocol.packet.register.RegisterResponseHeader;
import com.htjc.iot.protocol.packet.register.RegisterResponsePacket;
import com.htjc.iot.redis.Key;
import com.htjc.iot.redis.RedisService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-09-03 21:08
 */

@Service
@ChannelHandler.Sharable
@Slf4j
public class RegisterRequestHandler extends SimpleChannelInboundHandler<RegisterRequestPacket> {

    @Autowired
    private RedisService redisService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterRequestPacket msg) throws Exception {

        Header header = msg.getHeader();
        Long id = header.id();
        String token = produceToken(id);
        log.info("处理设备注册 : 设备id:{},生成token：{}", id, token);

        Key deviceKey = Key.DEVICE_KEY;
        redisService.setex(deviceKey.produceKey(token), String.class);
        Header respHeader = new RegisterResponseHeader();
        Body body = new Body(token);
        body.doEscape();

        RegisterResponsePacket registerResponsePacket = new RegisterResponsePacket(respHeader, body);
        ctx.channel().writeAndFlush(registerResponsePacket);
    }

    private String produceToken(long id) {
        String token = Hashing.md5().hashBytes(String.valueOf(id).getBytes()).toString();
        return token;
    }
}
