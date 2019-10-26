package com.cloud.base.handler;

import com.cloud.base.constants.Result;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Enumeration;
import java.util.Set;

/**
 * 全局异常处理类
 *
 * @author xujiping
 * @date 2018/6/14 16:41
 */
@ControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * 声明要捕获的异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result defultExcepitonHandler(HttpServletRequest request, Exception e) {
        Result result = new Result();
        if (e instanceof BusinessException) {
            log("业务异常", e, request);
            BusinessException businessException = (BusinessException) e;
            result.setCode(businessException.getCode());
            result.setMessage(businessException.getMessage());
            result.setData(businessException.getData());
        } else if (e instanceof ConstraintViolationException) {
            //参数校验异常
            result.setCode(ResultCode.PARAMS_ERROR.code());
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                result.setMessage(item.getMessage());
                Path propertyPath = item.getPropertyPath();
                String propertyStr = propertyPath.toString();
                if (propertyStr.contains("arg0")) {
                    result.setData("phone");
                } else if (propertyStr.contains("arg1")) {
                    result.setData("validateCode");
                } else if (propertyStr.contains("arg2")) {
                    result.setData("password");
                } else if (propertyStr.contains("arg3")) {
                    result.setData("repeatPassword");
                }
            }
            logger.debug("参数校验异常[code = " + result.getCode() + ", message = " + result.getMessage() + "]");
        } else {
            //未知错误
            logger.error("未知异常", e);
            log("未知异常", e, request);
            result.setResultCode(ResultCode.FAIL);
        }
        return result;
    }

    private void log(String title, Exception e, HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(">>>" + title);
        buffer.append("->请求地址[" + request.getRequestURL().toString() + "]");
        Enumeration<String> parameterNames = request.getParameterNames();
        buffer.append("->请求参数[");
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            buffer.append("        " + name + ":" + request.getParameter(name) + ",");
        }
        buffer.append("]");
        logger.error(buffer.toString());
    }
}
