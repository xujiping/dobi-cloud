package com.cloud.auth.jwt;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 05:52:15
 * @Version 1.0
 */
public class JwtUtil {

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken(String userId, String password) {
        String token = JWT.create()
                // 存入token中的信息
                .withAudience(userId)
                // token超时时间，1天之后过期
                .withExpiresAt(DateUtil.offsetDay(new DateTime(), 1))
                .sign(Algorithm.HMAC256(password));
        return token;
    }
}
