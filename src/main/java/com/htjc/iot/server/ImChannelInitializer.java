package com.htjc.iot.server;

import com.htjc.iot.codec.PacketCodecHandler;
import com.htjc.iot.handler.IotIdleStateHandler;
import com.htjc.iot.server.handler.AuthHandler;
import com.htjc.iot.server.handler.IMessageHandler;
import com.htjc.iot.server.handler.LoginRequestHandler;
import com.htjc.iot.server.handler.RegisterRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-08-01 15:49
 */

@Component
public class ImChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Autowired
    private IMessageHandler iMessageHandler;
    @Autowired
    private RegisterRequestHandler registerRequestHandler;
    @Autowired
    private LoginRequestHandler loginRequestHandler;


    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {

        // 空闲检测
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new IotIdleStateHandler());

        pipeline.addLast(PacketCodecHandler.INSTANCE);//in-out
        pipeline.addLast(registerRequestHandler);
        pipeline.addLast(loginRequestHandler);
//        pipeline.addLast(AuthHandler.INSTANCE);
        pipeline.addLast(iMessageHandler);
    }
}
