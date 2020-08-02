package com.hoolai.im.server.handler;

import com.alibaba.fastjson.JSON;
import com.hoolai.im.attribute.Attributes;
import com.hoolai.im.entiy.Recode;
import com.hoolai.im.protocol.request.HistoryRecodeRequestPacket;
import com.hoolai.im.protocol.response.HistoryRecodeResponsePacket;
import com.hoolai.im.redis.RedisService;
import com.hoolai.im.redis.key.impl.RecodeKey;
import com.hoolai.im.session.Session;
import com.hoolai.im.util.TimeService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 16:31
 */

@Service
@ChannelHandler.Sharable
public class HistoryRecodeRequestHandler extends SimpleChannelInboundHandler<HistoryRecodeRequestPacket> {

    @Autowired
    private RedisService redisService;
    @Autowired
    private TimeService timeService;

    private final int count = 10;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HistoryRecodeRequestPacket msg) throws Exception {
        String toUserId = msg.getToUserId();
        Long lastMsgTime = msg.getLastMsgTime();

        Session session = ctx.channel().attr(Attributes.SESSION).get();
        String openId = session.getOpenId();
        String key = RecodeKey.INSTANCE.produceKey(openId, toUserId);

        HistoryRecodeResponsePacket historyRecodeResponsePacket = new HistoryRecodeResponsePacket();
        Set<String> recodeSet = null;
        if (lastMsgTime == null) {
            recodeSet = redisService.zrevrangeByScore(key, timeService.getCurrentTime(), 10);
        } else {
            recodeSet = redisService.zrevrangeByScore(key, lastMsgTime, count);
        }
        List<Recode> recodes = recodeSet.stream().map(recode -> JSON.parseObject(recode, Recode.class)).sorted().collect(Collectors.toList());
        historyRecodeResponsePacket.setRecodes(recodes);
        ctx.channel().writeAndFlush(historyRecodeResponsePacket);
    }
}
