package com.hoolai.im.server;

import com.hoolai.im.codec.PacketCodecHandler;
import com.hoolai.im.server.handler.AuthHandler;
import com.hoolai.im.server.handler.IMHandler;
import com.hoolai.im.server.handler.LoginRequestHandler;
import com.hoolai.im.server.handler.RegisterRequestHandler;
import com.hoolai.im.util.SslUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLEngine;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-08-01 15:49
 */

@Component
public class ImChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Autowired
    private IMHandler imHandler;
    @Autowired
    private RegisterRequestHandler registerRequestHandler;
    @Autowired
    private LoginRequestHandler loginRequestHandler;



    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        //生成ssl
//        SSLEngine sslEngine = SslUtil.createSSLContext().createSSLEngine();
//        sslEngine.setUseClientMode(false);
//        sslEngine.setNeedClientAuth(false);

        // 空闲检测
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast("ssl", new SslHandler(sslEngine));

//                        pipeline.addLast(new IMIdleStateHandler());
        pipeline.addLast(new HttpServerCodec());
        // 聚合
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(PacketCodecHandler.INSTANCE);
        pipeline.addLast(registerRequestHandler);
        pipeline.addLast(loginRequestHandler);
        pipeline.addLast(AuthHandler.INSTANCE);
        pipeline.addLast(imHandler);
    }
}
