package com.cloud.auth.jwt;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cloud.base.util.MD5Util;

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
        if (StrUtil.isBlank(userId) || StrUtil.isBlank(password)) {
            return null;
        }
        String secret = MD5Util.MD5(password);
        if (StrUtil.isBlank(secret)) {
            return null;
        }
        return JWT.create()
                // 存入token中的信息
                .withAudience(userId)
                // token超时时间，1天之后过期
                .withExpiresAt(DateUtil.offsetDay(new DateTime(), 1))
                .sign(Algorithm.HMAC256(secret));
    }
}
