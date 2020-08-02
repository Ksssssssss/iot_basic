package com.hoolai.im.entiy;

import lombok.Data;

import java.util.Objects;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-20 21:21
 */
@Data
public class Friend implements Comparable<Friend> {
    private String friendId;
    private Recode recode;

    @Override
    public int compareTo(Friend o) {
        return (int)((this.recode.getMsgTime() - o.recode.getMsgTime())/1000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return Objects.equals(friendId, friend.friendId) &&
                Objects.equals(recode, friend.recode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendId, recode);
    }
}
