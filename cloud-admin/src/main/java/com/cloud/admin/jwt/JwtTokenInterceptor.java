package com.cloud.admin.jwt;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cloud.admin.entity.SysUser;
import com.cloud.admin.service.SysUserService;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * jwt Token 拦截器
 *
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 05:34:51
 * @Version 1.0
 */
@Slf4j
@AllArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    SysUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("拦截器开始，预处理");
        String token = request.getHeader("token");
        String accountId = request.getHeader("accountId");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (StrUtil.isBlank(token)) {
                    throw new BusinessException(ReturnCode.NO_TOKEN);
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new BusinessException(ReturnCode.TOKEN_FAIL);
                }
                if (StrUtil.isBlank(accountId) || !accountId.equals(userId)) {
                    throw new BusinessException(ReturnCode.TOKEN_FAIL);
                }
                SysUser user = userService.selectById(userId);
                if (user == null) {
                    throw new BusinessException(ReturnCode.USER_NOT_EXISTS);
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new BusinessException(ReturnCode.TOKEN_FAIL);
                }
                // 校验用户权限
                String platformId = request.getHeader("platform");
                String requestURI = request.getRequestURI();
                if (StrUtil.isBlank(platformId)) {
                    throw new BusinessException(ReturnCode.PARAMS_ERROR);
                }
                boolean checkPermission = userService.checkPermission(Integer.parseInt(platformId), userId, requestURI);
                if (!checkPermission) {
                    throw new BusinessException(ReturnCode.NO_PERMISSION);
                }
                return true;
            }
        }
        throw new BusinessException(ReturnCode.INVALID_REQUEST);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("拦截器开始，后处理");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("拦截器开始，处理完毕回调");

    }
}
