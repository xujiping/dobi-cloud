package com.cloud.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 后台管理系统
 *
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 05:15:57
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud.base", "com.cloud.db.config", "com.cloud.admin"})
@EnableSwagger2
@MapperScan(basePackages = {"com.cloud.admin.mapper"})
public class CloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAdminApplication.class, args);
    }
}
