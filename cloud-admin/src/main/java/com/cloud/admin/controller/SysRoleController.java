package com.cloud.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.SysRole;
import com.cloud.admin.entity.vo.RoleVo;
import com.cloud.admin.service.SysPermissionService;
import com.cloud.admin.service.SysRoleService;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.*;
import com.cloud.base.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@ResponseResult
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPermissionService permissionService;

    @UserLoginToken
    @ApiOperation(value = "分页列表", httpMethod = "GET")
    @GetMapping("page")
    public Page<RoleVo> listByPage(@ApiParam(name = "platform", value = "平台ID")
                                   @RequestHeader(required = false) Integer platform,
                                   @ApiParam(name = "page", value = "页码", defaultValue = "1")
                                   @RequestParam(required = false, defaultValue = "1") Integer page,
                                   @ApiParam(name = "size", value = "每页大小", defaultValue = "10")
                                   @RequestParam(required = false, defaultValue = "10") Integer size,
                                   @ApiParam(name = "platform", value = "平台ID")
                                   @RequestParam(required = false) Integer queryPlatform) {
        Map<String, Object> params = MapUtil.newHashMap(1);
        if (platform != null && platform != PlatformEnum.USER_CENTER.pId()) {
            params.put("platform_id", platform);
        }
        if (queryPlatform != null) {
            params.put("platform_id", queryPlatform);

        }
        Page<SysRole> rolePage = new Page<>(page, size);
        return roleService.listByPage(rolePage, params);
    }

    @UserLoginToken
    @ApiOperation(value = "创建角色", httpMethod = "POST")
    @PostMapping("new")
    public void add(
            @NotNull
            @ApiParam(required = true, name = "platform", value = "平台ID")
            @RequestParam Integer platform,
            @NotEmpty
            @ApiParam(required = true, name = "roleName", value = "角色名")
            @RequestParam String roleName,
            @NotEmpty
            @ApiParam(required = true, name = "roleIntro", value = "简介")
            @RequestParam String roleIntro) {
        boolean add = roleService.add(platform, roleName, roleIntro);
        if (!add) {
            throw new BusinessException(ResultCode.FAIL);
        }
    }

    @UserLoginToken
    @ApiOperation(value = "删除角色", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public void remove(@ApiParam(required = true, name = "id", value = "角色ID")
                       @PathVariable Integer id) {
        boolean remove = roleService.remove(id);
        if (!remove) {
            throw new BusinessException(ResultCode.FAIL);
        }
    }

    @UserLoginToken
    @ApiOperation(value = "给用户分配角色", httpMethod = "POST")
    @PostMapping("user")
    public void addUser(@ApiParam(required = true, name = "roleId", value = "角色ID")
                        @RequestParam Integer roleId,
                        @ApiParam(required = true, name = "userId", value = "用户ID")
                        @RequestParam String userId) {
        boolean addUser = roleService.addUser(userId, roleId);
        if (!addUser) {
            throw new BusinessException(ResultCode.FAIL);
        }
    }

    @UserLoginToken
    @ApiOperation(value = "给角色分配权限", httpMethod = "POST")
    @PostMapping("permission/add")
    public Boolean addPermission(HttpServletRequest request,
                                 @ApiParam(required = true, name = "roleId", value = "角色ID")
                                 @RequestParam Integer roleId,
                                 @ApiParam(required = true, name = "permissionIdList", value = "权限ID列表，用英文半角逗号分隔")
                                 @RequestParam String permissionIdList) {
        String accountId = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        roleService.addPermission(accountId, roleId, permissionIdList);
        return true;
    }

    @UserLoginToken
    @ApiOperation(value = "取消角色权限", httpMethod = "POST")
    @PostMapping("permission/remove")
    public Boolean removePermission(HttpServletRequest request,
                                    @ApiParam(required = true, name = "roleId", value = "角色ID")
                                    @RequestParam Integer roleId,
                                    @ApiParam(required = true, name = "permissionIdList", value = "权限ID列表，用英文半角逗号分隔")
                                    @RequestParam String permissionIdList) {
        String accountId = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        roleService.removePermission(accountId, roleId, permissionIdList);
        return true;
    }

    @UserLoginToken
    @ApiOperation(value = "角色权限ID列表", httpMethod = "GET", notes = "返回用户勾选的权限ID列表")
    @GetMapping("permissions")
    public int[] listRole(@ApiParam(required = true, name = "roleId", value = "角色ID")
                          @RequestParam Integer roleId) {
        return permissionService.listRole(roleId);
    }

}

