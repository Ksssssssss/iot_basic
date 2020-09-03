package com.htjc.iot.server.handler;

import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.Header;
import com.htjc.iot.protocol.packet.login.LoginRequestPacket;
import com.htjc.iot.protocol.packet.login.LoginResponseHeader;
import com.htjc.iot.protocol.packet.login.LoginResponsePacket;
import com.htjc.iot.redis.Key;
import com.htjc.iot.redis.RedisService;
import com.htjc.iot.session.Session;
import com.htjc.iot.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Autowired
    private RedisService redisService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        Body body = loginRequestPacket.getBody();
        Header header = loginRequestPacket.getHeader();
        body.doUnEscape();
        String token = body.getContent();
        log.info("设备发起登陆 : 设备token:{}", token);

        String s = redisService.get(Key.DEVICE_KEY.produceKey(token), String.class);
        if (s != null) {
            Channel channel = ctx.channel();
            SessionUtil.bindSession(new Session(token), channel);
            ctx.channel().writeAndFlush(new LoginResponsePacket(new LoginResponseHeader(), new Body()));
        } else {
            log.info("设备未登录，设备id ：{}", header.id());
            ctx.channel().close();
        }
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
