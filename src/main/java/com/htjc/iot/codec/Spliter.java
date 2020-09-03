package com.htjc.iot.codec;

import com.htjc.iot.protocol.packet.Header;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author ksssss
 * @Date: 20-9-2 22:00
 * @Description: 拆包解包
 */

public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 18;
    private static final int LENGTH_FIELD_LENGTH = 2;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != Header.FRAME_HEADER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
