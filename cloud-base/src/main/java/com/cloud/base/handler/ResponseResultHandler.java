package com.cloud.base.handler;

import com.cloud.base.constants.ResponseResult;
import com.cloud.base.constants.Result;
import com.cloud.base.interceptor.ResponseResultInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 处理是否需要包装返回
 *
 * @Author: xujiping
 * @Date: 2019年10月26日 0026 上午 09:43:04
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        // 判断请求是否有包装标记
        ResponseResult responseResult = (ResponseResult) request.getAttribute(ResponseResultInterceptor.RESPONSE_RESULT_ANN);
        return responseResult != null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("进入返回体 重写格式 处理中...");
        if (o instanceof Result){
            return o;
        }
        if (o instanceof String){
            return Result.success(o).toJson();
        }
        return Result.success(o);
    }
}
