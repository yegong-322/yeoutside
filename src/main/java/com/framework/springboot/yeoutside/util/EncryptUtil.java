package com.framework.springboot.yeoutside.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

/**
 * 암호화 Util class
*/
@Component
public class EncryptUtil {

    /**
     * SHA256 암호화
     * @param pw 암호화 대상
     */
    public String sha256(String pw) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(pw.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b: messageDigest.digest()) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
