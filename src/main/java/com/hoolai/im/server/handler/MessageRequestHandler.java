package com.hoolai.im.server.handler;

import com.alibaba.fastjson.JSON;
import com.hoolai.im.entiy.Recode;
import com.hoolai.im.protocol.response.ReplyBottleMessageResponsePacket;
import com.hoolai.im.redis.RedisService;
import com.hoolai.im.redis.key.impl.RecodeKey;
import com.hoolai.im.util.TimeService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.hoolai.im.protocol.request.MessageRequestPacket;
import com.hoolai.im.protocol.response.MessageResponsePacket;
import com.hoolai.im.session.Session;
import com.hoolai.im.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
@Slf4j
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TimeService timeService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        long msgTime = timeService.getCurrentTime();

        String toUserId = messageRequestPacket.getToUserId();

        Session session = SessionUtil.getSession(ctx.channel());
        String fromUserid = session.getOpenId();

        String message = messageRequestPacket.getMessage();
        Recode recode = new Recode(msgTime, message, fromUserid);
        String recodeMsg = JSON.toJSONString(recode);

        Recode toRecode = new Recode(msgTime, message, toUserId);
        String toRecodeMsg = JSON.toJSONString(toRecode);

        // 1.更新好友列表并记录消息
        redisService.flashFriendListAndRecodeMsg(fromUserid, toUserId, msgTime, toRecodeMsg, recodeMsg);

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(toUserId);

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            // 如果是回复瓶子，否则就是发送消息
            String srcMessage = messageRequestPacket.getBottleMessage();
            if (!StringUtils.isEmpty(srcMessage)) {
                long bottleTime = messageRequestPacket.getBottleTime();
                Recode srcRecode = new Recode(bottleTime, srcMessage, toUserId);
                redisService.zadd(RecodeKey.INSTANCE.produceKey(fromUserid, toUserId), bottleTime, JSON.toJSONString(srcRecode));

                ReplyBottleMessageResponsePacket replyBottleMessageResponsePacket = new ReplyBottleMessageResponsePacket();
                replyBottleMessageResponsePacket.setRecode(recode);
                replyBottleMessageResponsePacket.setNick(session.getUserNick());
                toUserChannel.writeAndFlush(replyBottleMessageResponsePacket);
            } else {

                // 2.通过消息发送方的会话信息构造要发送的消息
                MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
                messageResponsePacket.setMsgTime(msgTime);
                messageResponsePacket.setFromUserId(fromUserid);
                messageResponsePacket.setMessage(message);

                toUserChannel.writeAndFlush(messageResponsePacket).addListener(future -> {
                    if (future.isDone()) {

                    } else {

                    }

                });
            }
        } else {
            log.info("[] 不在线，发送失败!",session.getOpenId());
        }
    }
}
