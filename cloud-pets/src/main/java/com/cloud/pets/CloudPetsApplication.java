package com.cloud.pets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 宠物项目
 *
 * @author xujiping
 * @date 2019-05-11 23:02
 */
//@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud.base.config", "com.cloud.db.config", "com.cloud.pets"})
@EnableSwagger2
@MapperScan(basePackages = {"com.cloud.pets.mapper"})
public class CloudPetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudPetsApplication.class, args);
    }
}
