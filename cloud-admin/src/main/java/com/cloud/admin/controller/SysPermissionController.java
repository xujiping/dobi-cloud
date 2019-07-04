package com.cloud.admin.controller;

import com.cloud.admin.service.SysPermissionService;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.ReturnBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
@RestController
@RequestMapping("/sysPermission")
@Api(tags = "权限")
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @UserLoginToken
    @ApiOperation(value = "列表", httpMethod = "GET", notes = "查询权限列表，默认查询1级权限列表，后续根据upId查询下一级的权限列表")
    @GetMapping("list")
    public String list(@NotNull
                       @ApiParam(required = true, name = "ucToken", value = "用户token")
                       @RequestHeader String ucToken,
                       @ApiParam(name = "upId", value = "上级权限ID")
                       @RequestParam(required = false) Integer upId) {
        return new ReturnBean(permissionService.listChild(upId)).toJson();
    }

}

