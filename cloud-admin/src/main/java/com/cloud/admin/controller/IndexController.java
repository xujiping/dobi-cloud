package com.cloud.admin.controller;

import com.cloud.admin.jwt.UserLoginToken;
import com.cloud.base.constants.ReturnBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 05:44:08
 * @Version 1.0
 */
@RestController
public class IndexController {

    @UserLoginToken
    @GetMapping("")
    public String index() {
        return new ReturnBean().toJson();
    }

}
