package com.cloud.auth.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix="jwt")
@Data
public class JWTConfig {

    private List<String> includePathPatterns;

    private List<String> excludePathPatterns;

}
