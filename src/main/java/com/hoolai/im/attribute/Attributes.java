package com.hoolai.im.attribute;

import io.netty.util.AttributeKey;
import com.hoolai.im.session.Session;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
    AttributeKey<Long> BOTTLE_TIME = AttributeKey.newInstance("bottle_time");
}
