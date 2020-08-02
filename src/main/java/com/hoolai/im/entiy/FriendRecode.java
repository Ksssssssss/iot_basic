package com.hoolai.im.entiy;

import lombok.Data;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-31 17:44
 */

@Data
public class FriendRecode extends Recode {
    private String nick;

    public FriendRecode() {
    }

    public FriendRecode(long msgTime, String message, String userId, String nick) {
        super(msgTime, message, userId);
        this.nick = nick;
    }
}
