package com.cloud.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.SysRole;
import com.cloud.admin.service.SysRoleService;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * <p>
 * 平台角色 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
@RestController
@RequestMapping("/sysRole")
@Api(tags = "平台角色")
@Validated
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @UserLoginToken
    @ApiOperation(value = "分页列表", httpMethod = "GET")
    @GetMapping("page")
    public String listByPage(@NotNull
                             @ApiParam(required = true, name = "platform", value = "平台ID")
                             @RequestHeader Integer platform,
                             @ApiParam(name = "page", value = "页码", defaultValue = "1")
                             @RequestParam(required = false) Integer page,
                             @ApiParam(name = "size", value = "每页大小", defaultValue = "10")
                             @RequestParam(required = false) Integer size) {
        Map<String, Object> params = MapUtil.newHashMap(1);
        params.put("platform_id", platform);
        Page<SysRole> rolePage = new Page<>(page, size);
        rolePage = roleService.listByPage(rolePage, params);
        return new ReturnBean(rolePage).toJson();
    }

    @ApiOperation(value = "创建角色", httpMethod = "POST")
    @PostMapping("new")
    public String add(
            @NotNull
            @ApiParam(required = true, name = "platform", value = "平台ID")
            @RequestHeader Integer platform,
            @NotEmpty
            @ApiParam(required = true, name = "roleName", value = "角色名")
            @RequestParam String roleName,
            @NotEmpty
            @ApiParam(required = true, name = "roleIntro", value = "简介")
            @RequestParam String roleIntro) {
        ReturnBean rb = new ReturnBean(true);
        boolean add = roleService.add(platform, roleName, roleIntro);
        if (!add) {
            rb.setReturnCode(ReturnCode.FAIL, false);
        }
        return rb.toJson();
    }

    @ApiOperation(value = "给用户分配角色", httpMethod = "POST")
    @PostMapping("user")
    public String addUser(@ApiParam(required = true, name = "accountId", value = "用户ID")
                          @RequestHeader String accountId,
                          @ApiParam(required = true, name = "roleId", value = "角色ID")
                          @RequestParam Integer roleId) {
        ReturnBean rb = new ReturnBean(true);
        boolean addUser = roleService.addUser(accountId, roleId);
        if (!addUser) {
            rb.setReturnCode(ReturnCode.FAIL, false);
        }
        return rb.toJson();
    }

    @ApiOperation(value = "给角色分配权限", httpMethod = "POST")
    @PostMapping("permission")
    public String addPermission(@ApiParam(required = true, name = "accountId", value = "用户ID")
                                @RequestHeader String accountId,
                                @ApiParam(required = true, name = "roleId", value = "角色ID")
                                @RequestParam Integer roleId,
                                @ApiParam(required = true, name = "permissionId", value = "权限ID")
                                @RequestParam Integer permissionId) {
        ReturnBean rb = new ReturnBean(true);
        boolean addUser = roleService.addPermission(accountId, roleId, permissionId);
        if (!addUser) {
            rb.setReturnCode(ReturnCode.FAIL, false);
        }
        return rb.toJson();
    }

}

