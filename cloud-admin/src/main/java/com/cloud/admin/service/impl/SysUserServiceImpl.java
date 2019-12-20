package com.cloud.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.LoginLog;
import com.cloud.admin.entity.SysRolePermission;
import com.cloud.admin.entity.SysRoleUser;
import com.cloud.admin.entity.SysUser;
import com.cloud.admin.entity.dto.UserInfoDto;
import com.cloud.admin.entity.vo.UserOpenInfoVo;
import com.cloud.admin.entity.vo.UserVo;
import com.cloud.admin.mapper.SysUserMapper;
import com.cloud.admin.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.auth.jwt.JwtUtil;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 平台用户 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleUserService roleUserService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private OpenChannelService openChannelService;

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public SysUser get(String userId) {
        SysUser sysUser = selectById(userId);
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXISTS);
        }
        if (sysUser.getStatus() == Constants.STATUS_UNENABLE) {
            throw new BusinessException(ResultCode.USER_LOCKED);
        }
        return sysUser;
    }

    @Override
    public SysUser getByUsername(String username) {
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        return selectOne(wrapper);
    }

    @Override
    public SysUser addNormal(String username, String password) {
        SysUser user = new SysUser();
        user.setId(IdUtil.fastSimpleUUID());
        user.setUsername(username);
        user.setPassword(MD5Util.MD5(password));
        return insert(user) ? user : null;
    }

    @Override
    public UserVo login(String username, String password) {
        SysUser user = getByUsername(username);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXISTS);
        }
        Integer status = user.getStatus();
        if (MD5Util.MD5(password).equals(user.getPassword())) {
            String token = getToken(user.getId(), password);
            String name = user.getNickname();
            if (StrUtil.isBlank(name)) {
                name = user.getUsername();
            }
            return new UserVo(user.getId(), name, token, "", status);
        }
        return null;
    }

    @Override
    public Integer getRoleId(int platformId, String userId) {
        Wrapper<SysRoleUser> wrapper = new EntityWrapper<>();
        wrapper.eq("platform_id", platformId);
        wrapper.eq("user_id", userId);
        SysRoleUser roleUser = roleUserService.selectOne(wrapper);
        if (roleUser == null) {
            return null;
        }
        return roleUser.getRoleId();
    }

    @Override
    public boolean checkPermission(int platformId, String userId, String uri) {
        if (platformId == 0 || StrUtil.isBlank(userId) || StrUtil.isBlank(uri)) {
            return false;
        }
        Integer roleId = getRoleId(platformId, userId);
        if (roleId == null) {
            return false;
        }
        List<SysRolePermission> rolePermissions = permissionService.listByRole(roleId, null);
        if (rolePermissions != null && rolePermissions.size() > 0) {
            String checkUri;
            for (SysRolePermission rolePermission : rolePermissions) {
                checkUri = permissionService.getUri(rolePermission.getPermissionId());
                if (StrUtil.isNotBlank(checkUri) && checkUri.equals(uri)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Page<SysUser> listByPage(Page<SysUser> page, Map<String, Object> params) {
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        if (params.containsKey("status")) {
            wrapper.eq("status", params.get("status"));
        }
        page = selectPage(page, wrapper);
        List<SysUser> records = page.getRecords();
        if (records != null && records.size() > 0) {
            page.setTotal(selectCount(wrapper));
        }
        return page;
    }

    @Override
    public List<SysRoleUser> listByRole(Integer roleId) {
        Wrapper<SysRoleUser> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", roleId);
        return sysRoleUserService.selectList(wrapper);
    }

    @Override
    public boolean updateStatus(String userId, Integer status) {
        if (StrUtil.isBlank(userId) || status == null) {
            return false;
        }
        SysUser sysUser = selectById(userId);
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXISTS);
        }
        sysUser.setStatus(status);
        return updateById(sysUser);
    }

    @Override
    public UserVo loginByWx(String openId, String appName) {
        if (StrUtil.isBlank(openId)) {
            return null;
        }
        SysUser user = new SysUser();
        String userId = openChannelService.getUserId(Constants.CHANNEL_TYPE_WX, openId);
        if (StrUtil.isBlank(userId)) {
            // 新用户注册
            userId = IdUtil.fastSimpleUUID();
            user.setId(userId);
            user.setUsername(openId);
            user.setAppName(appName);
            user.setStatus(Constants.STATUS_NOT_INIT);
            boolean insert = insert(user);
            if (!insert) {
                return null;
            }
            openChannelService.add(openId, Constants.CHANNEL_TYPE_WX, null, userId);
        }
        user = get(userId);
        String token = getToken(userId, openId);
        return new UserVo(userId, user.getNickname(), token, user.getAvatar(), user.getStatus());
    }

    @Override
    public void update(String userId, UserInfoDto userInfoDto) {
        if (StrUtil.isBlank(userId) || userInfoDto == null) {
            throw new BusinessException();
        }
        SysUser user = get(userId);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXISTS);
        }
        user.setNickname(userInfoDto.getNickName());
        user.setAvatar(userInfoDto.getAvatarUrl());
        user.setStatus(Constants.STATUS_NORMAL);
        if (!updateById(user)) {
            throw new BusinessException();
        }
    }

    @Override
    public String getToken(String userId, String secret) {
        String token = JwtUtil.getToken(userId, secret);
        if (StrUtil.isNotBlank(token)) {
            loginLogService.add(token, secret, userId);
        }
        return token;
    }

    @Override
    public LoginLog getByToken(String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        Wrapper<LoginLog> wrapper = new EntityWrapper<>();
        wrapper.eq("token", token);
        return loginLogService.selectOne(wrapper);
    }

    @Override
    public UserOpenInfoVo getOpenInfo(String userId) {
        UserOpenInfoVo openInfoVo = new UserOpenInfoVo();
        SysUser sysUser = selectById(userId);
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXISTS);
        }
        BeanUtils.copyProperties(sysUser, openInfoVo);
        openInfoVo.setUserId(userId);
        return openInfoVo;
    }
}
