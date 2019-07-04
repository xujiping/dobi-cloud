package com.cloud.admin.service;

import com.cloud.admin.entity.SysPermission;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.entity.SysRolePermission;
import com.cloud.admin.entity.vo.PermissionVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 获取URI
     * @param id 主键ID
     * @return String
     */
    String getUri(int id);

    /**
     * 菜单列表
     * @param platform 平台ID
     * @param userId 用户ID
     * @param level 级别
     * @return
     */
    List<PermissionVo> list(int platform, String userId, Integer level);

    /**
     * 查询下一级菜单列表
     * @param
     * @return
     */
    List<PermissionVo> listChild(Integer upId);

    /**
     * 角色权限列表
     * @param roleId 角色ID
     * @return
     */
    List<SysRolePermission> listByRole(int roleId, Integer level);

    PermissionVo wrapper(SysRolePermission rolePermission);

    PermissionVo wrapper(SysPermission permission);

}
