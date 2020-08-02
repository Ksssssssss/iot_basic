package com.hoolai.im.entiy;

import lombok.Data;

import java.util.Objects;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-21 21:13
 */

@Data
public class Recode implements Comparable<Recode> {
    protected long msgTime;
    protected String message;
    protected String userId;

    public Recode() {
    }

    public Recode(long msgTime, String message,String userId) {
        this.msgTime = msgTime;
        this.message = message;
        this.userId = userId;
    }

    @Override
    public int compareTo(Recode o) {
        return (int) (this.msgTime - o.msgTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recode recode = (Recode) o;
        return msgTime == recode.msgTime &&
                Objects.equals(message, recode.message) &&
                Objects.equals(userId, recode.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgTime, message, userId);
    }
}
