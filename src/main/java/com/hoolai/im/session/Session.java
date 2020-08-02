package com.hoolai.im.session;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Session {
    // 用户唯一性标识
    private String openId;
    private String userNick;

    public Session(String openId, String userNick) {
        this.openId = openId;
        this.userNick = userNick;
    }

    @Override
    public String toString() {
        return openId + ":" + userNick;
    }

}
