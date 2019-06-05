package com.cloud.admin.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cloud.admin.entity.User;

/**
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 05:52:15
 * @Version 1.0
 */
public class JwtUtil {

    /**
     * 获取token
     * @param user
     * @return
     */
    public static String getToken(User user) {
        String token = JWT.create()
                // 存入token中的信息
                .withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
