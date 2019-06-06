package com.cloud.admin.controller;

import com.cloud.admin.entity.SysUser;
import com.cloud.admin.entity.vo.UserVo;
import com.cloud.admin.jwt.JwtUtil;
import com.cloud.admin.jwt.PassToken;
import com.cloud.admin.service.SysUserService;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * 注册
 *
 * @Author: xujiping
 * @Date: 2019年6月6日 0006 上午 10:50:27
 * @Version 1.0
 */
@RestController
@RequestMapping("signIn")
@Api(tags = "注册")
@Validated
public class SignInController {

    @Autowired
    private SysUserService userService;

    @PassToken
    @ApiOperation(value = "普通注册", httpMethod = "POST", notes = "用户名、密码注册")
    @PostMapping("normal")
    public String normal(@NotEmpty
                         @ApiParam(required = true, name = "username", value = "用户名")
                         @RequestParam String username,
                         @Length(min = 6, max = 20)
                         @ApiParam(required = true, name = "password", value = "密码")
                         @RequestParam String password) {
        SysUser user = userService.getByUsername(username);
        if (user != null) {
            throw new BusinessException(ReturnCode.USER_EXIST);
        }
        user = userService.addNormal(username, password);
        if (user == null) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        // 生成token
        String token = JwtUtil.getToken(user);
        UserVo userVo = new UserVo(user.getId(), token);
        return new ReturnBean(userVo).toJson();
    }
}
