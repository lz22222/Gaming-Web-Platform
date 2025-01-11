package cn.cie.utils;

import org.springframework.util.DigestUtils;

/**
 * Encrypt the password with MD5
 */
public class PasswordUtil {

    private static final String SALT = "gp30#?>2a";

    public static String pwd2Md5(String paswword) {
        return DigestUtils.md5DigestAsHex((paswword + SALT).getBytes());
    }

}
