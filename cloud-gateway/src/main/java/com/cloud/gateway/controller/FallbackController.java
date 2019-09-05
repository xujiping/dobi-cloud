package com.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xujiping
 * @Date: 2019年9月5日 0005 上午 10:41:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("")
    public String fallback(){
        return "error";
    }
}
