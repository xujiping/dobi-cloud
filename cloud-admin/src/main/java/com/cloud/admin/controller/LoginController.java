package com.cloud.admin.controller;

import com.cloud.admin.entity.vo.UserVo;
import com.cloud.admin.jwt.PassToken;
import com.cloud.admin.service.SysUserService;
import com.cloud.base.constants.ReturnBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: xujiping
 * @Date: 2019年6月6日 0006 上午 11:34:18
 * @Version 1.0
 */
@RestController
@RequestMapping("login")
@Validated
public class LoginController {

    @Autowired
    private SysUserService userService;

    @PassToken
    @ApiOperation(value = "普通登录", httpMethod = "POST", notes = "用户名、密码登录")
    @PostMapping("normal")
    public String normal(@NotEmpty
                         @ApiParam(required = true, name = "username", value = "用户名")
                         @RequestParam String username,
                         @NotEmpty
                         @ApiParam(required = true, name = "password", value = "密码")
                         @RequestParam String password) {
        UserVo userVo = userService.login(username, password);
        return new ReturnBean(userVo).toJson();
    }
}
