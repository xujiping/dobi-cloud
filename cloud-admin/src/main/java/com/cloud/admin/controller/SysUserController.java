package com.cloud.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.SysRole;
import com.cloud.admin.entity.SysUser;
import com.cloud.admin.service.SysPermissionService;
import com.cloud.admin.service.SysUserService;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * <p>
 * 平台用户 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-06-05
 */
@RestController
@RequestMapping("/sysUser")
@Api(tags = "平台用户")
@Validated
public class SysUserController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @UserLoginToken
    @ApiOperation(value = "用户信息", httpMethod = "GET")
    @GetMapping("info")
    public String menuList(HttpServletRequest request,
                           @NotNull
                           @ApiParam(required = true, name = "UcToken", value = "用户token")
                           @RequestHeader String token) {
        String accountId = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        return new ReturnBean(userService.get(accountId)).toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "菜单列表", httpMethod = "GET")
    @GetMapping("menu")
    public String menuList(@NotNull
                           @ApiParam(required = true, name = "platform", value = "平台ID")
                           @RequestHeader Integer platform,
                           @NotBlank
                           @ApiParam(required = true, name = "accountId", value = "用户ID")
                           @RequestHeader String accountId,
                           @NotNull
                           @ApiParam(required = true, name = "level", value = "菜单级别")
                           @RequestParam Integer level) {
        return new ReturnBean(permissionService.list(platform, accountId, level)).toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "分页列表", httpMethod = "GET")
    @GetMapping("page")
    public String listByPage(@ApiParam(name = "page", value = "页码", defaultValue = "1")
                             @RequestParam(required = false) Integer page,
                             @ApiParam(name = "size", value = "每页大小", defaultValue = "10")
                             @RequestParam(required = false) Integer size,
                             @ApiParam(name = "status", value = "状态： null所有 0不可用 1正常")
                             @RequestParam(required = false) Integer status) {
        Map<String, Object> params = MapUtil.newHashMap(1);
        if (status != null) {
            params.put("status", status);
        }
        Page<SysUser> userPage = new Page<>(page, size);
        userPage = userService.listByPage(userPage, params);
        return new ReturnBean(userPage).toJson();
    }

}

