package com.hoolai.im.entiy;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-20 17:41
 */

@Data
public class User {
    private String openId;
    private String wxName;
    private String avatarUrl;
    private String nick;
    /**
     * 0男、1女、2未知
     */
    private byte gender;
    private int age;

    public User() {
    }

    public User(String openId, String wxName, String avatarUrl) {
        this.openId = openId;
        this.wxName = wxName;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "openId='" + openId + '\'' +
                ", wxName='" + wxName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nick='" + nick + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
