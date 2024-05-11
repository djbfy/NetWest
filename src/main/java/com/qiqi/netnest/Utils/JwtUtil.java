package com.qiqi.netnest.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    //签名
    private static final String SECRET_KEY = "qiqi";
    //过期时间 24个小时
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    /**
     * 生成 token 不设置用户信息
     */
    public static String createToken() {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                //到期时间
                .withExpiresAt(date)
                //添加签名
                .sign(algorithm);
    }

    /**
     * 生成 token
     */
    public static String createToken(String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                //到期时间
                .withExpiresAt(date)
                //添加签名
                .sign(algorithm);
    }

    /**
     * 校验 token 是否正确
     */
    public static boolean verify(String token) {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        try {
            JWT.require(algorithm)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     */
    public static Object getMsg(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username");
    }

}
