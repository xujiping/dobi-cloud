package com.cloud.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.entity.SysRolePermission;
import com.cloud.admin.entity.SysRoleUser;
import com.cloud.admin.entity.vo.RoleVo;

import java.util.Map;

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
     *
     * @param platform 平台ID
     * @param name     角色名
     * @param intro    简介
     * @return boolean
     */
    boolean add(int platform, String name, String intro);

    /**
     * 查询
     *
     * @param platform 平台ID
     * @param name     角色名
     * @return SysRole
     */
    SysRole get(int platform, String name);

    /**
     * 获取用户角色ID
     *
     * @param platform 平台ID
     * @param userId   用户ID
     * @return
     */
    Integer getRoleId(int platform, String userId);

    /**
     * 获取用户的平台角色
     * @param platform
     * @param userId
     * @return
     */
    SysRoleUser getUserRole(int platform, String userId);

    /**
     * 检查是否已经存在用户角色
     * @param userId
     * @param roleId
     * @return
     */
    boolean existUserRole(String userId, int roleId);

    /**
     * 给用户分配角色
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return boolean
     */
    boolean addUser(String userId, int roleId);

    /**
     * 给角色分配权限
     *
     * @param userId       授权者
     * @param roleId       角色ID
     * @param permissionIdList 权限ID列表
     * @return boolean
     */
    void addPermission(String userId, int roleId, String permissionIdList);

    /**
     * 取消权限
     *
     * @param userId       授权者
     * @param roleId       角色ID
     * @param permissionIdList 权限ID列表
     * @return boolean
     */
    void removePermission(String userId, int roleId, String permissionIdList);

    /**
     * 分页
     *
     * @param page
     * @param params
     * @return
     */
    Page<RoleVo> listByPage(Page<SysRole> page, Map<String, Object> params);

    /**
     * 包装vo
     * @param sysRole
     * @return
     */
    RoleVo wrapper(SysRole sysRole);

    /**
     * 查询角色权限
     * @param roleId
     * @param permissionId
     * @return
     */
    SysRolePermission getRolePermission(Integer roleId, Integer permissionId);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    boolean remove(Integer roleId);

}
