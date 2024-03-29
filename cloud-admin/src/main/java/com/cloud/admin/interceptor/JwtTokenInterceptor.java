package com.cloud.admin.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cloud.admin.entity.LoginLog;
import com.cloud.admin.entity.SysUser;
import com.cloud.admin.service.SysUserService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
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
        String requestURI = request.getRequestURI();
        log.info("拦截器开始，预处理：" + requestURI);
        String token = request.getHeader(Constants.HEADER_TOKEN);
        String accountId = request.getParameter(Constants.HEADER_ACCOUNT_ID);
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
                    log.error("token为空");
                    throw new BusinessException(ResultCode.NO_TOKEN);
                }
                // 查询用户信息
                SysUser user = userService.selectById(accountId);
                if (user == null) {
                    log.error("用户为空，userId={}", accountId);
                    throw new BusinessException(ResultCode.USER_NOT_EXISTS);
                }
                String username = user.getUsername();
                LoginLog loginLog = userService.getByToken(token);
                if (loginLog == null) {
                    log.error("登陆记录不存在, token={}", token);
                    throw new BusinessException(ResultCode.TOKEN_FAIL);
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(loginLog.getSecret())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    log.error("token校验失败, token={}", token);
                    throw new BusinessException(ResultCode.TOKEN_FAIL);
                }
                // admin用户不校验权限
                if (!username.equals("dobi")) {
                    if (!requestURI.contains("/sysUser")) {
                        // 校验用户权限
                        String platformId = request.getHeader("platform");
                        if (StrUtil.isBlank(platformId)) {
                            throw new BusinessException(ResultCode.PARAMS_ERROR);
                        }
                        boolean checkPermission = userService.checkPermission(Integer.parseInt(platformId), accountId
                                , requestURI);
                        if (!checkPermission) {
                            throw new BusinessException(ResultCode.NO_PERMISSION);
                        }
                    }
                }
                return true;
            }
        }
        throw new BusinessException(ResultCode.INVALID_REQUEST);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        log.info("拦截器后处理");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        log.info("拦截器处理完毕回调");

    }
}
