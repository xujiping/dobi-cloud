package com.cloud.admin.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.SysUser;
import com.cloud.admin.entity.dto.UserInfoDto;
import com.cloud.admin.entity.vo.PermissionVo;
import com.cloud.admin.entity.vo.UserOpenInfoVo;
import com.cloud.admin.service.SysPermissionService;
import com.cloud.admin.service.SysUserService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResponseResult;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
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
@ResponseResult
public class SysUserController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @UserLoginToken
    @ApiOperation(value = "用户信息", httpMethod = "GET")
    @GetMapping("info")
    public SysUser userInfo(HttpServletRequest request) {
        String accountId = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        return userService.get(accountId);
    }

    @PassToken
    @ApiOperation(value = "用户开放信息", httpMethod = "GET")
    @GetMapping("info/open/{id}")
    public UserOpenInfoVo openUserInfo(@PathVariable(value = "id") String id) {
        return userService.getOpenInfo(id);
    }

    @UserLoginToken
    @ApiOperation(value = "菜单列表", httpMethod = "GET")
    @GetMapping("menu")
    public List<PermissionVo> menuList(HttpServletRequest request,
                                       @NotNull
                                       @ApiParam(required = true, name = "platform", value = "平台ID")
                                       @RequestHeader Integer platform,
                                       @NotNull
                                       @ApiParam(required = true, name = "level", value = "菜单级别")
                                       @RequestParam Integer level) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        return permissionService.list(platform, key, level);
    }

    @UserLoginToken
    @ApiOperation(value = "分页列表", httpMethod = "GET")
    @GetMapping("page")
    public Page<SysUser> listByPage(@ApiParam(name = "page", value = "页码", defaultValue = "1")
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
        return userPage;
    }

    @UserLoginToken
    @ApiOperation(value = "更新用户状态", httpMethod = "POST")
    @PostMapping("update/status")
    public void updateStatus(@ApiParam(required = true, name = "userId", value = "用户ID")
                             @RequestParam String userId,
                             @ApiParam(required = true, name = "status", value = "状态")
                             @RequestParam Integer status) {
        boolean update = userService.updateStatus(userId, status);
        if (!update) {
            throw new BusinessException(ResultCode.FAIL);
        }
    }

    @UserLoginToken
    @ApiOperation(value = "初始化用户基本信息", httpMethod = "POST")
    @PostMapping("info/init")
    public void initInfo(HttpServletRequest request,
                         @ApiParam(required = true, name = "userInfoDto", value = "用户信息")
                         @RequestBody UserInfoDto userInfoDto) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        if (StrUtil.isBlank(key)) {
            throw new BusinessException(ResultCode.TOKEN_FAIL);
        }
        userService.update(key, userInfoDto);
    }


}

