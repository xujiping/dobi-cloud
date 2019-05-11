package com.cloud.pets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 宠物项目
 * @author xujiping
 * @date 2019-05-11 23:02
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudPetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudPetsApplication.class, args);
    }
}
