package com.cloud.auth.jwt;

import com.cloud.base.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 域名拦截
 */
public class CorsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        if (Constants.HTTP_STR_OPTIONS.equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(200);
        }
        String origin = httpServletRequest.getHeader("Origin");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-URL-PATH, Origin, X-Requested-With, X_Requested_With, Content-Type, Accept, lmt-token, user-id, openid, x-access-token, Access-Control-Allow-Origin,Set-Cookie,Cookie,userFlag,ucToken,platform");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("XDomainRequestAllowed", "1");
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Set-Cookie, userFlag, Content-Length, Cookie");
        if (StringUtils.isNotBlank(httpServletRequest.getHeader("userFlag"))) {
            httpServletResponse.setHeader("userFlag", httpServletRequest.getHeader("userFlag"));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
    //其他postHandle,afterCompletion空继承
}
