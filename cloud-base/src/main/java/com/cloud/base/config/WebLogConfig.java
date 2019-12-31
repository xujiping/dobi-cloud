package com.cloud.base.config;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.base.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * HTTP日志
 *
 * @author xujiping
 */
@Aspect
@Component
@Slf4j
public class WebLogConfig {

    @Pointcut("execution(public * com.cloud.*.controller..*.*(..))" +
            " || execution(public * com.cloud.*.fast.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 同一个IP2秒访问了2000次，加入黑名单表记录，暂时不做拦截
     *
     * @param joinPoint
     * @param result
     * @throws Throwable
     */
    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        String ip = IpUtils.getIpAddr(request);
        String url = request.getRequestURL().toString();
        log.info("****** IP : " + ip);
        log.info("****** URL : " + url);
        log.info("****** HTTP_METHOD : " + request.getMethod());
        log.info("****** CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
                .getSignature().getName());
        log.info("****** ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 处理完请求，返回内容
        String responseStr = null;
        if (result != null) {
            responseStr = JSON.toJSONString(result);
        }
        log.info("****** RESPONSE : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
                .getSignature().getName() + ": \n" + responseStr);
    }
}
