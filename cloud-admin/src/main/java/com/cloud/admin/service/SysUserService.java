package com.cloud.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.SysRole;
import com.cloud.admin.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.entity.vo.UserVo;

import java.util.Map;

/**
 * <p>
 * 平台用户 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-05
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 查询
     * @param username 用户名
     * @return SysUser
     */
    SysUser getByUsername(String username);

    /**
     *  创建用户
     * @param username 用户名
     * @param password 密码
     * @return boolean
     */
    SysUser addNormal(String username, String password);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return UserVo
     */
    UserVo login(String username, String password);

    /**
     * 获取用户角色
     * @param platformId 平台ID
     * @param userId 用户ID
     * @return int
     */
    Integer getRoleId(int platformId, String userId);

    /**
     * 判断用户是否拥有权限
     * @param platformId 平台ID
     * @param userId 用户ID
     * @param uri URI
     * @return boolean
     */
    boolean checkPermission(int platformId, String userId, String uri);

    /**
     * 分页
     *
     * @param page
     * @param params
     * @return
     */
    Page<SysUser> listByPage(Page<SysUser> page, Map<String, Object> params);

}
