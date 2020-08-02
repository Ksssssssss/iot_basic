//package com.hoolai.im.server.handler;
//
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelHandlerContext;
//
//@ChannelHandler.Sharable
//public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
//    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();
//
//    private HeartBeatRequestHandler() {
//
//    }
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
//        ctx.writeAndFlush(new HeartBeatResponsePacket());
//    }
//}
