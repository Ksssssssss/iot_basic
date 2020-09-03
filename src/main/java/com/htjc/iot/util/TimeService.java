package com.htjc.iot.util;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-23 15:59
 */

@Service
@Data
public class TimeService {
    private long currentTime;

    @PostConstruct
    private void init() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentTime = System.currentTimeMillis();
            }
        }).start();
    }
}
