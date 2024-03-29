package com.cloud.admin.controller;

import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.ResponseResult;
import com.cloud.base.constants.Result;
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
@ResponseResult
public class IndexController {

    @PassToken
    @GetMapping("")
    public String index() {
        return "sd";
    }

    @PassToken
    @GetMapping("auth")
    public String auth(){
        return "不需要认证";
    }

}
