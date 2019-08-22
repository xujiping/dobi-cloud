package com.cloud.auth.jwt;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
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

    /**
     * 校验token
     *
     * @param userCenterConfig
     * @param token
     * @return
     */
    public static void validate(UserCenterConfig userCenterConfig, String token) {
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new BusinessException(ReturnCode.NO_TOKEN);
        }
        // 查询用户信息，此时已经在用户中心校验了一次token
        ReturnBean returnBean = UcHttpUtil.get(userCenterConfig.getRequestUser(), token, null);
        if (!returnBean.isSuccess()) {
            throw new BusinessException(returnBean.getCode(), returnBean.getMsg());
        }
//        JSONObject userObject = (JSONObject) returnBean.getData();
//        String password = userObject.getString("secret");
//        // 验证 token
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();
//        try {
//            jwtVerifier.verify(token);
//        } catch (JWTVerificationException e) {
//            throw new BusinessException(ReturnCode.TOKEN_FAIL);
//        }
        // 校验用户权限
//                String platformId = request.getHeader(Constants.HEADER_PLATFORM);
//                if (StrUtil.isBlank(platformId)) {
//                    throw new BusinessException(ReturnCode.PARAMS_ERROR);
//                }
////                boolean checkPermission = userService.checkPermission(Integer.parseInt(platformId), userId, requestURI);
////                if (!checkPermission) {
////                    throw new BusinessException(ReturnCode.NO_PERMISSION);
////                }
    }

    /**
     * 从token中获取用户ID
     *
     * @param userCenterConfig
     * @param token
     * @return
     */
    public static String getUserId(UserCenterConfig userCenterConfig, String token) {
        // 执行认证
        if (StrUtil.isBlank(token)) {
            return null;
        }
        // 查询用户信息
        ReturnBean returnBean = UcHttpUtil.get(userCenterConfig.getRequestUser(), token, null);
        if (!returnBean.isSuccess()) {
            return null;
        }
        JSONObject userObject = (JSONObject) returnBean.getData();
        return userObject.getString("id");
    }
}
