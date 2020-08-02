package com.hoolai.im.server.handler;

import com.alibaba.fastjson.JSON;
import com.hoolai.im.attribute.Attributes;
import com.hoolai.im.entiy.Recode;
import com.hoolai.im.protocol.request.FriendListRequestPacket;
import com.hoolai.im.protocol.response.FriendListResponsePacket;
import com.hoolai.im.redis.RedisService;
import com.hoolai.im.redis.key.impl.FriendListKey;
import com.hoolai.im.session.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 14:42
 */

@ChannelHandler.Sharable
@Service
public class FriendListRequestHandler extends SimpleChannelInboundHandler<FriendListRequestPacket> {

    @Autowired
    private RedisService redisService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FriendListRequestPacket msg) throws Exception {
//        FriendListResponsePacket responsePacket = new FriendListResponsePacket();
//        Session session = ctx.channel().attr(Attributes.SESSION).get();
//        String openId = session.getOpenId();
//        Map<String, String> friendMap = redisService.hgetAll(FriendListKey.INSTANCE.produceKey(openId));
//        List<Recode> friends = friendMap.values().stream().map(friend -> JSON.parseObject(friend, Recode.class)).sorted().collect(Collectors.toList());
//
//        responsePacket.setFriendList(friends);
//        ctx.writeAndFlush(responsePacket);
    }
}
