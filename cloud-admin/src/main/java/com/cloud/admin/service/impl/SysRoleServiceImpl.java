package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.SysRole;
import com.cloud.admin.entity.SysRolePermission;
import com.cloud.admin.entity.SysRoleUser;
import com.cloud.admin.entity.vo.RoleVo;
import com.cloud.admin.mapper.SysRoleMapper;
import com.cloud.admin.service.SysRolePermissionService;
import com.cloud.admin.service.SysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.admin.service.SysRoleUserService;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.PlatformEnum;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 平台角色 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleUserService roleUserService;

    @Autowired
    private SysRolePermissionService rolePermissionService;

    @Override
    public boolean add(int platform, String name, String intro) {
        if (PlatformEnum.getpName(platform) == null) {
            throw new BusinessException(ReturnCode.PLATFORM_NOT_EXIST);
        }
        SysRole role = get(platform, name);
        if (role != null) {
            throw new BusinessException(ReturnCode.ROLE_EXIST);
        }
        role = new SysRole();
        role.setPlatformId(platform);
        role.setRoleName(name);
        role.setRoleIntro(intro);
        return insert(role);
    }

    @Override
    public SysRole get(int platform, String name) {
        Wrapper<SysRole> wrapper = new EntityWrapper<>();
        wrapper.eq("platform_id", platform);
        wrapper.eq("role_name", name);
        return selectOne(wrapper);
    }

    @Override
    public Integer getRoleId(int platform, String userId) {
        Wrapper<SysRoleUser> wrapper = new EntityWrapper<>();
        wrapper.eq("platform_id", platform);
        wrapper.eq("user_id", userId);
        SysRoleUser roleUser = roleUserService.selectOne(wrapper);
        if (roleUser != null) {
            return roleUser.getRoleId();
        }
        return null;
    }

    @Override
    public boolean existUserRole(String userId, int roleId) {
        Wrapper<SysRoleUser> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", roleId);
        wrapper.eq("user_id", userId);
        return roleUserService.selectOne(wrapper) != null;
    }

    @Override
    public boolean addUser(String userId, int roleId) {
        SysRole role = selectById(roleId);
        if (role == null || role.getStatus() == Constants.STATUS_UNENABLE) {
            throw new BusinessException(ReturnCode.ROLE_NULL_OR_UNENABLE);
        }
        boolean existUserRole = existUserRole(userId, roleId);
        if (existUserRole){
            return true;
        }
        SysRoleUser roleUser = new SysRoleUser();
        roleUser.setRoleId(roleId);
        roleUser.setPlatformId(role.getPlatformId());
        roleUser.setUserId(userId);
        return roleUserService.insert(roleUser);
    }

    @Override
    public boolean addPermission(String userId, int roleId, int permissionId) {
        SysRolePermission rolePermission = new SysRolePermission();
        rolePermission.setCreateUserId(userId);
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        return rolePermissionService.insert(rolePermission);
    }

    @Override
    public Page<RoleVo> listByPage(Page<SysRole> page, Map<String, Object> params) {
        Page<RoleVo> voPage = new Page<>();
        List<RoleVo> roleVoList = new ArrayList<>();
        Wrapper<SysRole> wrapper = new EntityWrapper<>();
        if (params.containsKey("platform_id")) {
            wrapper.eq("platform_id", params.get("platform_id"));
        }
        page = selectPage(page, wrapper);
        List<SysRole> records = page.getRecords();
        if (records != null && records.size() > 0) {
            roleVoList = records.stream().map(this::wrapper).collect(Collectors.toList());
            voPage.setRecords(roleVoList);
            voPage.setTotal(selectCount(wrapper));
        }
        return voPage;
    }

    @Override
    public RoleVo wrapper(SysRole sysRole) {
        RoleVo roleVo = new RoleVo();
        if (sysRole == null) {
            return roleVo;
        }
        BeanUtils.copyProperties(sysRole, roleVo);
        roleVo.setPlatform(PlatformEnum.getpName(roleVo.getPlatformId()));
        return roleVo;
    }
}
