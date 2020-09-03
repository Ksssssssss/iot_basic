package com.htjc.iot.client.handler;

import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.Header;
import com.htjc.iot.protocol.packet.login.LoginRequestHeader;
import com.htjc.iot.protocol.packet.login.LoginRequestPacket;
import com.htjc.iot.protocol.packet.register.RegisterResponsePacket;
import com.htjc.iot.protocol.status.StatusCode;
import com.htjc.iot.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.manager.util.SessionUtils;

/**
 * @author ksssss
 * @Date: 20-9-3 14:30
 * @Description:
 */
@Slf4j
@Getter
public class RegisterResponseHandler extends SimpleChannelInboundHandler<RegisterResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterResponsePacket msg) throws Exception {
        Header header = msg.getHeader();
        if (header.code() != StatusCode.SUCCESS.code) {
            log.info("register fail");
            return;
        }
        String tokenContent = msg.getBody().getContent();
        log.info("注册成功 , 接受到token:{}", tokenContent);

        log.info("发起登陆,token:{}", tokenContent);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket(new LoginRequestHeader(), new Body(tokenContent));
        ctx.channel().writeAndFlush(loginRequestPacket);
    }
}
