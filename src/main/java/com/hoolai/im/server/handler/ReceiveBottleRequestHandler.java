package com.hoolai.im.server.handler;

import com.hoolai.im.attribute.Attributes;
import com.hoolai.im.entiy.Recode;
import com.hoolai.im.entiy.User;
import com.hoolai.im.protocol.request.ReceiveBottleRequestPacket;
import com.hoolai.im.protocol.request.ReceiveBottleResponsePacket;
import com.hoolai.im.redis.RedisService;
import com.hoolai.im.redis.key.impl.BottleKey;
import com.hoolai.im.redis.key.impl.FriendListKey;
import com.hoolai.im.redis.key.impl.UserKey;
import com.hoolai.im.session.Session;
import com.hoolai.im.util.TimeService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 10:03
 */

@Service
@ChannelHandler.Sharable
public class ReceiveBottleRequestHandler extends SimpleChannelInboundHandler<ReceiveBottleRequestPacket> {
    @Autowired
    private RedisService redisService;
    @Autowired
    private TimeService timeService;

    private Random random = new Random();
    // cursor并不是线程安全的
    private volatile String cursor = "0";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ReceiveBottleRequestPacket msg) throws Exception {
        Session session = ctx.channel().attr(Attributes.SESSION).get();
        String openId = session.getOpenId();

        //随机出bottle keys
        ScanResult<String> stringScanResult = redisService.randomBottleKeys(BottleKey.INSTANCE, cursor, 1);
        cursor = stringScanResult.getCursor();
        List<String> result = stringScanResult.getResult();

        boolean flag = false;
        ReceiveBottleResponsePacket receiveBottleResponsePacket = new ReceiveBottleResponsePacket();
        if (!CollectionUtils.isEmpty(result)) {
            //随机出key和根据key随机出一个瓶子
            long currentTime = timeService.getCurrentTime();

            String key = result.get((int) (currentTime % result.size()));
            Recode bottle = redisService.randomBottle(key);
            if (bottle != null && !bottle.getUserId().equals(openId)) {
                flag = true;
                User user = redisService.hget(UserKey.INSTANCE.prefix(), bottle.getUserId(), User.class);
                receiveBottleResponsePacket.setUser(user);
                receiveBottleResponsePacket.setRecode(bottle);
            }
        }

        receiveBottleResponsePacket.setSuccess(flag);
        ctx.writeAndFlush(receiveBottleResponsePacket);
    }
}
