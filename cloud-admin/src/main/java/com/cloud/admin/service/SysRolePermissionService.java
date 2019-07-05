package com.cloud.admin.service;

import com.cloud.admin.entity.SysRolePermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 获取角色权限列表
     * @param roleId
     * @return
     */
    List<SysRolePermission> listByRole(Integer roleId);

}
