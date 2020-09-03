package com.htjc.iot.util;

import com.htjc.iot.attribute.Attributes;
import com.htjc.iot.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SessionUtil {
    private static final Map<String, Channel> deviceChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        deviceChannelMap.put(session.getToken(), channel);
        channel.attr(Attributes.SESSION).set(session);
        log.info("登陆成功{}", session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            deviceChannelMap.remove(session.getToken());
            channel.attr(Attributes.SESSION).set(null);
            log.info("退出登陆{}", session);
        }
    }

    public static boolean hasLogin(Channel channel) {

        return getSession(channel) != null;
    }

    public static boolean hasLogin(String openId) {

        return deviceChannelMap.containsKey(openId);
    }

    public static Session getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String token) {

        return deviceChannelMap.get(token);
    }
}
