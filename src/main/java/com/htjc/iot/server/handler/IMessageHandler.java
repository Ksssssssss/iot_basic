package com.htjc.iot.server.handler;

import com.htjc.iot.protocol.Packet;
import com.htjc.iot.protocol.command.Command;
import com.htjc.iot.protocol.packet.Header;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@ChannelHandler.Sharable
public class IMessageHandler extends SimpleChannelInboundHandler<Packet> {

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    @PostConstruct
    private void init() {
        handlerMap = new HashMap<>(Command.values().length);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        Header header = packet.header();
        handlerMap.get(header.command())
                .channelRead(ctx, packet);
    }
}
