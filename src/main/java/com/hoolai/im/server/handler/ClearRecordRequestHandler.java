package com.hoolai.im.server.handler;

import com.hoolai.im.attribute.Attributes;
import com.hoolai.im.protocol.request.ClearRecordRequestPacket;
import com.hoolai.im.protocol.response.ClearRecordResponsePacket;
import com.hoolai.im.redis.RedisService;
import com.hoolai.im.redis.key.impl.FriendListKey;
import com.hoolai.im.redis.key.impl.RecodeKey;
import com.hoolai.im.session.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-25 14:41
 */

@Service
@ChannelHandler.Sharable
public class ClearRecordRequestHandler extends SimpleChannelInboundHandler<ClearRecordRequestPacket> {

    @Autowired
    private RedisService redisService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ClearRecordRequestPacket msg) throws Exception {
        Session session = ctx.channel().attr(Attributes.SESSION).get();
        String openId = session.getOpenId();
        Map<String, String> friends = redisService.hgetAll(FriendListKey.INSTANCE.produceKey(openId));
        friends.keySet().forEach(key -> {
            redisService.hdel(FriendListKey.INSTANCE.produceKey(key), openId);
            redisService.zrem(RecodeKey.INSTANCE.produceKey(key, openId));
        });
        redisService.hdel(FriendListKey.INSTANCE.produceKey(openId));

        ClearRecordResponsePacket clearRecordResponsePacket = new ClearRecordResponsePacket();
        clearRecordResponsePacket.setSuccess(false);
        ctx.writeAndFlush(clearRecordResponsePacket);
    }
}
