package com.cloud.admin.service;

import com.cloud.admin.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 平台角色 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 创建角色
     * @param platform 平台ID
     * @param name 角色名
     * @param intro 简介
     * @return boolean
     */
    boolean add(int platform, String name, String intro);

    /**
     * 查询
     * @param platform 平台ID
     * @param name 角色名
     * @return SysRole
     */
    SysRole get(int platform, String name);

    /**
     * 获取用户角色ID
     * @param platform 平台ID
     * @param userId 用户ID
     * @return
     */
    Integer getRoleId(int platform, String userId);

    /**
     * 给用户分配角色
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return boolean
     */
    boolean addUser(String userId, int roleId);

    /**
     * 给角色分配权限
     * @param userId 授权者
     * @param roleId 角色ID
     * @param permissionId 权限ID
     * @return boolean
     */
    boolean addPermission(String userId, int roleId, int permissionId);

}
