package com.qiqi.netnest.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswdUtil {
    /**
     * 传进来的密码进行加密
     * @param ps
     * @return
     */
    public static String generatePass(String ps){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(ps);
    }
    public static boolean judPass(String ps1,String ps2){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(ps1, ps2);
    }
}
