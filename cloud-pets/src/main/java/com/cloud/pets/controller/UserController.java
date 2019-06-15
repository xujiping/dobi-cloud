package com.cloud.pets.controller;

import com.cloud.auth.jwt.UserCenterConfig;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.ReturnBean;
import com.cloud.pets.entity.vo.UcUserVo;
import com.cloud.pets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Value("${file.server}")
    private String fileServer;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCenterConfig userCenterConfig;

    @UserLoginToken
    @ApiOperation(value = "获取用户信息", httpMethod = "GET", response = UcUserVo.class, notes = "获取用户信息，冻结用户除外")
    @GetMapping("info")
    public String info(@ApiParam(required = true, name = "UcToken", value = "用户token")
                       @RequestHeader String token) {
        ReturnBean rb = new ReturnBean();
        UcUserVo user = userService.getFromUc(token, userCenterConfig);
        if (user != null) {
            rb.setData(user);
        }
        return rb.toJson();
    }

}

