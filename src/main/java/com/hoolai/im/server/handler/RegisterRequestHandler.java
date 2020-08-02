package com.hoolai.im.server.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hoolai.im.entiy.User;
import com.hoolai.im.protocol.request.RegisterRequestPacket;
import com.hoolai.im.protocol.response.RegisterResponsePacket;
import com.hoolai.im.redis.RedisService;
import com.hoolai.im.redis.key.impl.UserKey;
import com.hoolai.im.util.AesCbcUtil;
import com.hoolai.im.util.WxUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-24 21:08
 */

@Service
@ChannelHandler.Sharable
@Slf4j
public class RegisterRequestHandler extends SimpleChannelInboundHandler<RegisterRequestPacket> {

    @Autowired
    private RedisService redisService;

    private AtomicLong atomicLong = new AtomicLong(0);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterRequestPacket msg) throws Exception {

        String code = msg.getCode();
        byte gender = msg.getGender();
        String nick = msg.getNick();
        int age = msg.getAge();
        String encryptedData = msg.getEncryptedData();
        String iv = msg.getIv();

        Objects.requireNonNull(code);
        JSONObject wxInfo = WxUtil.doGetWxInfo(code);
        String result = AesCbcUtil.decrypt(encryptedData, wxInfo.getString("session_key"), iv, "UTF-8");
        JSONObject info = JSON.parseObject(result);
        String openId = wxInfo.getString("openid");

        User user = redisService.hget(UserKey.INSTANCE.prefix(), openId, User.class);
        RegisterResponsePacket registerResponsePacket = new RegisterResponsePacket();
        if (user == null) {
            String name = info.getString("nickName");
            String avatarUrl = info.getString("avatarUrl");

            user = new User(openId, name, avatarUrl);
            user.setNick(nick);
            user.setGender(gender);
            user.setAge(age);
            redisService.hset(UserKey.INSTANCE.prefix(), openId, JSON.toJSONString(user));

            long playerNum = atomicLong.incrementAndGet();
            log.info("玩家注册{},注册人数{}", user, playerNum);
            registerResponsePacket.setSuccess(true);
        } else {
            registerResponsePacket.setSuccess(false);
            registerResponsePacket.setReason("账号已经注册");
        }
        ctx.channel().writeAndFlush(registerResponsePacket);
    }
}
