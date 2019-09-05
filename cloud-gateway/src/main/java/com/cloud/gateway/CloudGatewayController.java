package com.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 网关中心
 *
 * @Author: xujiping
 * @Date: 2019年9月5日 0005 上午 10:18:12
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class CloudGatewayController {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayController.class, args);
    }

}
