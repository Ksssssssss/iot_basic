package com.htjc.iot.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    /**
     * 设备唯一性标识
     */
    private String token;

}
