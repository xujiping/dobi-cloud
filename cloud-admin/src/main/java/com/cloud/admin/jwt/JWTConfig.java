package com.cloud.admin.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {

    @Value("${jwt.include-path-patterns}")
    private String[] includePathPatterns;

    @Value("${jwt.exclude-path-patterns}")
    private String[] excludePathPatterns;

    public String[] getIncludePathPatterns() {
        return includePathPatterns;
    }

    public void setIncludePathPatterns(String[] includePathPatterns) {
        this.includePathPatterns = includePathPatterns;
    }

    public String[] getExcludePathPatterns() {
        return excludePathPatterns;
    }

    public void setExcludePathPatterns(String[] excludePathPatterns) {
        this.excludePathPatterns = excludePathPatterns;
    }
}
