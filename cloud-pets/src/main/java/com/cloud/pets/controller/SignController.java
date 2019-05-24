package com.cloud.pets.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.base.constants.Constants;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.entity.User;
import com.cloud.pets.service.TokenService;
import com.cloud.pets.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登陆、注册控制器
 * @author xujiping
 * @date 2018-12-29 17:43
 */
@RequestMapping("")
@RestController
@Validated
public class SignController {

    @Autowired private UserService userService;

    @Autowired private TokenService tokenService;

    @ApiOperation(value = "普通注册", httpMethod = "PUT", response = String.class, notes = "普通的用户名、密码注册")
    @PutMapping("signUp")
    public String signUp(
            @ApiParam(required = true, name = "username", value = "用户名")
                    @Length(min = 8, max = 50)
                    @RequestParam String username,
            @ApiParam(required = true, name = "password", value = "密码")
                    @Length(min = 6, max = 20)
                    @RequestParam String password){
        com.cloud.base.constants.ReturnBean rb = new com.cloud.base.constants.ReturnBean();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean insert = userService.insert(user);
        if (!insert){
            throw new BusinessException(com.cloud.base.constants.ReturnCode.FAIL);
        }
        return rb.toJsonString();
    }

    @ApiOperation(value = "普通登陆", httpMethod = "POST", response = String.class, notes = "普通的登陆，返回用户token，客户端保存token")
    @PostMapping("signIn")
    public String signIn(@ApiParam(required = true, name = "username", value = "用户名")
                         @NotBlank
                         @RequestParam String username,
                         @ApiParam(required = true, name = "password", value = "密码")
                         @NotBlank
                         @RequestParam String password){
        com.cloud.base.constants.ReturnBean rb = new com.cloud.base.constants.ReturnBean();
        User user = userService.get(username);
        if (user == null){
            throw new BusinessException(com.cloud.base.constants.ReturnCode.LOGIN_FAIL);
        }
        String password1 = user.getPassword();
        if (!password.equals(password1)){
            throw new BusinessException(com.cloud.base.constants.ReturnCode.LOGIN_FAIL);
        }
        String status = user.getStatus();
        if (Constants.STAT_BLOCK.equals(status)){
            throw new BusinessException(com.cloud.base.constants.ReturnCode.USER_BLOCK);
        }
        Long key = user.getId();
        // 登陆成功，生成新的token
        String newToken = tokenService.generate(String.valueOf(key));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", newToken);
        jsonObject.put("key", key);
        rb.setData(jsonObject);
        return rb.toJsonString();
    }
}
