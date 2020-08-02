package com.hoolai.im.server.handler;

import com.alibaba.fastjson.JSON;
import com.hoolai.im.attribute.Attributes;
import com.hoolai.im.entiy.Recode;
import com.hoolai.im.entiy.User;
import com.hoolai.im.protocol.request.SendBottleRequestPacket;
import com.hoolai.im.protocol.request.SendBottleResponsePacket;
import com.hoolai.im.redis.RedisService;
import com.hoolai.im.redis.key.impl.BottleKey;
import com.hoolai.im.redis.key.impl.UserKey;
import com.hoolai.im.session.Session;
import com.hoolai.im.util.TimeService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.compression.Snappy;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 10:03
 */

@Service
@ChannelHandler.Sharable
@Slf4j
public class SendBottleRequestHandler extends SimpleChannelInboundHandler<SendBottleRequestPacket> {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TimeService timeService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendBottleRequestPacket msg) throws Exception {
        Session session = ctx.channel().attr(Attributes.SESSION).get();
        String openId = session.getOpenId();
        Recode recode = new Recode(timeService.getCurrentTime(), msg.getMessage(), openId);

        redisService.saveBottle(BottleKey.INSTANCE, openId, JSON.toJSONString(recode));
        SendBottleResponsePacket sendBottleResponsePacket = new SendBottleResponsePacket();
        sendBottleResponsePacket.setSuccess(true);
        log.info("{} send bottle,msg : {}", session.getUserNick(), msg.getMessage());
        ctx.writeAndFlush(sendBottleResponsePacket);
    }
}
