package com.htjc.iot.client.handler;

import com.htjc.iot.protocol.packet.Header;
import com.htjc.iot.protocol.packet.login.LoginResponsePacket;
import com.htjc.iot.protocol.status.StatusCode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ksssss
 * @Date: 20-9-3 16:04
 * @Description:
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        Header header = msg.getHeader();
        if (header.code() == StatusCode.SUCCESS.code) {
            log.info("设备登陆成功");
        } else {
            log.info("设备登陆失败");
            ctx.channel().close();
        }
    }
}
