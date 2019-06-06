package com.cloud.admin.controller;

import com.cloud.admin.service.SysPermissionService;
import com.cloud.base.constants.ReturnBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 平台用户 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-06-05
 */
@Controller
@RequestMapping("/sysUser")
@Api(tags = "平台用户")
@Validated
public class SysUserController {

    @Autowired
    private SysPermissionService permissionService;

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

}

