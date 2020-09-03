package com.htjc.iot.protocol.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author ksssss
 * @date 20-9-2
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Body {
    /**
     * 内容
     *
     * @return
     */
    private String content;

    /**
     * 转义
     */
    public void doEscape() {
    }

    /**
     * 反转义
     */

    public void doUnEscape() {
    }
}
