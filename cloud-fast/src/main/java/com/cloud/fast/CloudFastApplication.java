package com.cloud.fast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 快速单品综合项目
 *
 * @author xujiping
 * @date 2019-08-01 17:33
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud.base", "com.cloud.db.config", "com.cloud.auth.jwt", "com.cloud.fast"})
@EnableSwagger2
@MapperScan(basePackages = {"com.cloud.fast.mapper"})
@EnableConfigurationProperties
public class CloudFastApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFastApplication.class, args);
    }
}
