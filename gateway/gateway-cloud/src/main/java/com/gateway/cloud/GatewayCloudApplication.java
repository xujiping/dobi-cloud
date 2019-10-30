package com.gateway.cloud;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujiping
 * @date 2019-10-21 16:01
 */
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableCaching
@EnableDiscoveryClient
@EnableConfigurationProperties
@NacosConfigurationProperties(dataId = "gateway-cloud", autoRefreshed = true)
@EnableFeignClients
public class GatewayCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayCloudApplication.class, args);
    }

}
