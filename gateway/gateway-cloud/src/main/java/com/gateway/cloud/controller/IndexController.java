package com.gateway.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xujiping
 * @Date: 2019年10月22日 0022 上午 10:18:10
 * @Version 1.0
 */
@RestController
// 刷新配置
@RefreshScope
public class IndexController {

    @Value("${env}")
    private String env;

    @GetMapping("env")
    public String index() {
        return env;
    }
}
