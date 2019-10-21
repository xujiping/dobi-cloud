package com.cloud.admin.controller;

import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.ReturnBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 05:44:08
 * @Version 1.0
 */
@RestController
@Api(tags = "默认")
public class IndexController {

    @PassToken
    @GetMapping("")
    public String index() {
        return new ReturnBean().toJson();
    }

}
