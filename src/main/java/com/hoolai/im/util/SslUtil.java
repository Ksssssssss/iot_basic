package com.hoolai.im.util;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2020-08-01 14:34
 */

public class SslUtil {
    private static final String type = "JKS";
    private static final String path = "src/main/resources/gryffindor.xyz.jks";
    private static final String password = "703lk16ow08d";
    private static volatile SSLContext sslContext = null;

    public static SSLContext createSSLContext() throws Exception {
        if (null == sslContext){
            synchronized (SslUtil.class){
                if (null == sslContext){
                    KeyStore sslKey = KeyStore.getInstance(type);
                    InputStream sslIn = new FileInputStream(path);
                    sslKey.load(sslIn,password.toCharArray());
                    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    kmf.init(sslKey,password.toCharArray());
                    sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(kmf.getKeyManagers(),null,null);
                }
            }
        }
        return sslContext;
    }
}
