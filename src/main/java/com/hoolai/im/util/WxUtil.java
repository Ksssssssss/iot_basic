package com.hoolai.im.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-07-20 19:27
 */

public class WxUtil {
    public static final String SECRET = "423b9d850ce249f72e62962c22d68f19";
    public static final String APPID = "wx1be02a27b7657448";
    public static final String APPURL = "https://api.weixin.qq.com/sns/jscode2session";

    public static JSONObject doGetWxInfo(String code) {
        String url = APPURL + "?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String data = client(url, HttpMethod.GET, new LinkedMultiValueMap<>());
        return JSON.parseObject(data);
    }

    public static String client(String url, HttpMethod method, MultiValueMap<String, String> params) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        return response.getBody();
    }
}
