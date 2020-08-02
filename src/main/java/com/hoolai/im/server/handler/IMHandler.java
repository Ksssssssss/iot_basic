package com.hoolai.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.hoolai.im.protocol.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.hoolai.im.protocol.command.Command.*;

@Service
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    @Autowired
    private MessageRequestHandler messageRequestHandler;
    @Autowired
    private SendBottleRequestHandler sendBottleRequestHandler;
    @Autowired
    private ReceiveBottleRequestHandler receiveBottleRequestHandler;
    @Autowired
    private HistoryRecodeRequestHandler historyRecodeRequestHandler;
    @Autowired
    private ClearRecordRequestHandler clearRecordRequestHandler;

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    @PostConstruct
    private void init() {
        handlerMap = new HashMap<>(values().length);
        handlerMap.put(MESSAGE_REQUEST.command, messageRequestHandler);
        handlerMap.put(SEND_BOTTLE_REQUEST.command, sendBottleRequestHandler);
        handlerMap.put(RECEIVE_BOTTLE_REQUEST.command, receiveBottleRequestHandler);
        handlerMap.put(HISTORY_RECODE_REQUEST.command, historyRecodeRequestHandler);
        handlerMap.put(CLEAR_RECODE_REQUEST.command, clearRecordRequestHandler);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
