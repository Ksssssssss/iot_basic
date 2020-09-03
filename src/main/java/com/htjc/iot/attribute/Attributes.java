package com.htjc.iot.attribute;

import io.netty.util.AttributeKey;
import com.htjc.iot.session.Session;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
