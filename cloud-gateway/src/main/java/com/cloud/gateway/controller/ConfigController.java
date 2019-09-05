package com.cloud.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xujiping
 * @Date: 2019年9月5日 0005 下午 01:55:13
 * @Version 1.0
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${haha:}")
    private String username;

    @RequestMapping("/get")
    public String get() {
        return username;
    }

    @GetMapping("")
    public String hello(){
        return "hello";
    }
}
