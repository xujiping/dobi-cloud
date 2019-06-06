package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.entity.SysPermission;
import com.cloud.admin.entity.SysRolePermission;
import com.cloud.admin.entity.vo.PermissionVo;
import com.cloud.admin.mapper.SysPermissionMapper;
import com.cloud.admin.service.SysPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.admin.service.SysRolePermissionService;
import com.cloud.admin.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysRolePermissionService rolePermissionService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public String getUri(int id) {
        SysPermission sysPermission = selectById(id);
        if (sysPermission != null) {
            return sysPermission.getUri();
        }
        return null;
    }

    @Override
    public List<PermissionVo> list(int platform, String userId, Integer level) {
        List<PermissionVo> permissionVoList = new ArrayList<>();
        Integer roleId = roleService.getRoleId(platform, userId);
        if (roleId == null) {
            return null;
        }
        List<SysRolePermission> rolePermissions = listByRole(roleId, level);
        if (rolePermissions != null && rolePermissions.size() > 0) {
            permissionVoList = rolePermissions.stream().map(this::wrapper).collect(Collectors.toList());
        }
        return permissionVoList;
    }

    @Override
    public List<SysRolePermission> listByRole(int roleId, int level) {
        Wrapper<SysRolePermission> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", roleId);
        wrapper.eq("level", level);
        return rolePermissionService.selectList(wrapper);
    }

    @Override
    public PermissionVo wrapper(SysRolePermission rolePermission) {
        PermissionVo permissionVo = new PermissionVo();
        if (rolePermission == null){
            return permissionVo;
        }
        Integer permissionId = rolePermission.getPermissionId();
        SysPermission permission = selectById(permissionId);
        if (permission == null){
            return permissionVo;
        }
        BeanUtils.copyProperties(permission, permissionVo);
        return permissionVo;
    }
}
