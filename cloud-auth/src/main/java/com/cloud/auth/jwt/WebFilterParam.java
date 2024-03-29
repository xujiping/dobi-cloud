package com.cloud.auth.jwt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.Result;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.exception.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 解密token之后把accountId添加到请求参数中
 *
 * @Author: xujiping
 * @Date: 2019年6月15日 0015 下午 01:47:05
 * @Version 1.0
 */
@Component
@WebFilter(urlPatterns = "/test", filterName = "webFilterParam")
@Slf4j
public class WebFilterParam extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String token = request.getHeader(Constants.HEADER_TOKEN);
        String userId = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        log.info("开始过滤URL=[" + requestURI + "]");
        PrintWriter out = null;
        if (StrUtil.isBlank(token) && StrUtil.isBlank(userId)) {
            filterChain.doFilter(request, response);
        } else {
            if (StrUtil.isBlank(token)) {
                throw new InvalidTokenException(ResultCode.NO_TOKEN);
            }
            // 获取token中的 userId
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                throw new InvalidTokenException(ResultCode.TOKEN_FAIL);
            }
            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
            //修改请求参数
            parameterMap.put(Constants.HEADER_ACCOUNT_ID, new String[]{userId});
            ParameterServletRequestWrapper req = new ParameterServletRequestWrapper(request, parameterMap);
//        ParameterServletResponseWrapper resp = new ParameterServletResponseWrapper(response);
            //调用对应的controller
            filterChain.doFilter(req, response);
//        //对返回参数进行处理
//        resp.setContentLength(-1);
//        RequestParam requestParam = JSONObject.toJavaObject(JSON.parseObject(new String(resp.getResponseData())),
//        RequestParam.class);
//        requestParam.setData1("****");
//        requestParam.setData2("****");
//
        }
    }
}
