package com.cloud.admin.controller;

import com.cloud.admin.service.SysRoleService;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 平台角色 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
@Controller
@RequestMapping("/sysRole")
@Api(tags = "平台角色")
@Validated
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

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

