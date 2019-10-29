package com.cloud.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * dobi自定义配置
 * todo 后续放到nacos上
 * @author xujiping
 * @date 2019-10-29 10:31
 */
@Configuration
public class DobiConfig {

    @Value("${dobi.admin.user}")
    private String adminUser;
}
