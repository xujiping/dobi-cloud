package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.entity.SysRolePermission;
import com.cloud.admin.mapper.SysRolePermissionMapper;
import com.cloud.admin.service.SysRolePermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public List<SysRolePermission> listByRole(Integer roleId) {
        if (roleId == null){
            return null;
        }
        Wrapper<SysRolePermission> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", roleId);
        return selectList(wrapper);
    }
}
