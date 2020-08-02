//package com.hoolai.im.server.handler;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.hoolai.im.entiy.User;
//import com.hoolai.im.protocol.request.AuthorRequestPacket;
//import com.hoolai.im.protocol.response.AuthorResponsePacket;
//import com.hoolai.im.redis.RedisService;
//import com.hoolai.im.redis.key.impl.AuthorKey;
//import com.hoolai.im.redis.key.impl.UserKey;
//import com.hoolai.im.util.SessionUtil;
//import com.hoolai.im.util.WxUtil;
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@ChannelHandler.Sharable
//@Service
//public class FirstAuthorHandler extends SimpleChannelInboundHandler<AuthorRequestPacket> {
//
//    @Autowired
//    private RedisService redisService;
//
//    protected FirstAuthorHandler() {
//    }
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, AuthorRequestPacket authorRequestPacket) {
//        String code = authorRequestPacket.getCode();
//        Objects.requireNonNull(code);
//
//        JSONObject wxInfo = WxUtil.doGetWxInfo(code);
//        String openId = wxInfo.getString("openid");
//        Boolean isAuthor = redisService.hget(AuthorKey.INSTANCE.prefix(), openId, Boolean.class);
//
//        AuthorResponsePacket authorResponsePacket = new AuthorResponsePacket();
//        authorResponsePacket.setSuccess(true);
//        if (Objects.isNull(isAuthor) || !isAuthor) {
//            authorResponsePacket.setSuccess(false);
//        }
//
//        ctx.writeAndFlush(authorRequestPacket);
//    }
//}
