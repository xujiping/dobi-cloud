package com.cloud.admin.config;

import com.cloud.admin.interceptor.JwtTokenInterceptor;
import com.cloud.admin.service.SysUserService;
import com.cloud.auth.jwt.CorsInterceptor;
import com.cloud.auth.jwt.JWTConfig;
import com.cloud.auth.jwt.UserCenterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 05:37:17
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JWTConfig jwtConfig;

    @Autowired private UserCenterConfig userCenterConfig;

    @Autowired private SysUserService userService;

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor(userService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(jwtTokenInterceptor())
                .addPathPatterns(jwtConfig.getIncludePathPatterns())
                .excludePathPatterns(jwtConfig.getExcludePathPatterns());
    }
}
