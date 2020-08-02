package com.hoolai.im.server.handler;

import com.alibaba.fastjson.JSON;
import com.hoolai.im.entiy.Friend;
import com.hoolai.im.entiy.FriendRecode;
import com.hoolai.im.entiy.Recode;
import com.hoolai.im.entiy.User;
import com.hoolai.im.protocol.response.FriendListResponsePacket;
import com.hoolai.im.redis.RedisService;
import com.hoolai.im.redis.key.impl.FriendListKey;
import com.hoolai.im.redis.key.impl.UserKey;
import com.hoolai.im.util.WxUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.hoolai.im.protocol.request.LoginRequestPacket;
import com.hoolai.im.protocol.response.LoginResponsePacket;
import com.hoolai.im.session.Session;
import com.hoolai.im.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Autowired
    private RedisService redisService;
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        String code = loginRequestPacket.getCode();
        String openId = WxUtil.doGetWxInfo(code).getString("openid");
        User user = redisService.hget(UserKey.INSTANCE.prefix(), openId, User.class);

        if (user != null) {
            loginResponsePacket.setNick(user.getNick());
            loginResponsePacket.setOpenId(openId);
            loginResponsePacket.setSuccess(true);
            SessionUtil.bindSession(new Session(openId, user.getNick()), ctx.channel());

            sendFriendListResponse(ctx, openId);
        } else {
            loginResponsePacket.setReason("用户没注册");
            loginResponsePacket.setSuccess(false);
        }

        // 登录响应
        ctx.writeAndFlush(loginResponsePacket);
    }

    private void sendFriendListResponse(ChannelHandlerContext ctx, String openId) {
        FriendListResponsePacket friendListResponsePacket = new FriendListResponsePacket();
        Map<String, String> friendMap = redisService.hgetAll(FriendListKey.INSTANCE.produceKey(openId));
        List<User> users = redisService.hgetAll(UserKey.INSTANCE.prefix(), friendMap.keySet(), User.class);

        List<FriendRecode> friends = users.stream().map(user -> {
            String nick = user.getNick();
            FriendRecode friendRecode = JSON.parseObject(friendMap.get(user.getOpenId()), FriendRecode.class);
            friendRecode.setNick(nick);
            return friendRecode;
        }).sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());


        friendListResponsePacket.setFriendList(friends);
        ctx.writeAndFlush(friendListResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
