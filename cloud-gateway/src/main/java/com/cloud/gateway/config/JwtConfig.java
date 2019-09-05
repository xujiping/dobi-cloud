package com.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * jwt token 路径拦截
 * @author xujiping
 */
@Component
@ConfigurationProperties(prefix="jwt")
@Data
public class JwtConfig {

    private List<String> includePathPatterns;

    private List<String> excludePathPatterns;

}
