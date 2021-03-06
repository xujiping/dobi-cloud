package com.cloud.admin.controller;

import com.cloud.admin.entity.vo.PermissionTree;
import com.cloud.admin.entity.vo.PermissionVo;
import com.cloud.admin.service.SysPermissionService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResponseResult;
import com.cloud.base.constants.ReturnBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

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
@ResponseResult
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @UserLoginToken
    @ApiOperation(value = "列表", httpMethod = "GET", notes = "查询权限列表，默认查询1级权限列表，后续根据upId查询下一级的权限列表")
    @GetMapping("list")
    public List<PermissionTree> list(@NotNull
                                     @ApiParam(name = "upId", value = "上级权限ID")
                                     @RequestParam(required = false) Integer upId) {
        return permissionService.listChild(upId);
    }

    @PassToken
    @ApiOperation(value = "全部权限列表", httpMethod = "GET", notes = "全部权限列表，并返回用户勾选的权限ID列表")
    @GetMapping("all")
    public List<PermissionTree> all() {
        return permissionService.listAll();
    }

}

