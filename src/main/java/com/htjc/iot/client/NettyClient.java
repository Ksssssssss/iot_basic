package com.htjc.iot.client;

import com.htjc.iot.client.handler.LoginResponseHandler;
import com.htjc.iot.client.handler.RegisterResponseHandler;
import com.htjc.iot.codec.PacketCodecHandler;
import com.htjc.iot.codec.Spliter;
import com.htjc.iot.protocol.packet.Body;
import com.htjc.iot.protocol.packet.login.LoginRequestHeader;
import com.htjc.iot.protocol.packet.login.LoginRequestPacket;
import com.htjc.iot.protocol.packet.register.RegisterRequestHeader;
import com.htjc.iot.protocol.packet.register.RegisterRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author ksssss
 * @Date: 20-9-2 15:26
 * @Description:
 */

@Slf4j
public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {

                        ch.pipeline().addLast(new PacketCodecHandler());
                        //  注册
                        ch.pipeline().addLast(new RegisterResponseHandler());
                        ch.pipeline().addLast(new LoginResponseHandler());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                Channel channel = ((ChannelFuture) future).channel();
                startThread(channel);
            } else if (retry == 0) {
                log.error("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                log.info("{} : 连接失败，第" + order + "次重连……", new Date());
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startThread(Channel channel) {

        new Thread(() -> {
            log.info("发起注册");
            RegisterRequestHeader header = new RegisterRequestHeader();
            RegisterRequestPacket registerRequestPacket = new RegisterRequestPacket(header, new Body());
            channel.writeAndFlush(registerRequestPacket);

        }).start();
    }
}
