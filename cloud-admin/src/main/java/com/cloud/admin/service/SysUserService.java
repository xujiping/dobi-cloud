package com.cloud.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.entity.LoginLog;
import com.cloud.admin.entity.SysRoleUser;
import com.cloud.admin.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.entity.dto.UserInfoDto;
import com.cloud.admin.entity.vo.UserVo;

import java.util.List;
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
     * @param userId 主键
     * @return
     */
    SysUser get(String userId);


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

    /**
     * 查询所属角色的用户列表
     * @param roleId
     * @return
     */
    List<SysRoleUser> listByRole(Integer roleId);

    /**
     * 更新用户状态
     * @param userId
     * @param status
     * @return
     */
    boolean updateStatus(String userId, Integer status);

    /**
     * 根据微信open_id获取登陆信息
     * @param openId
     * @return
     */
    UserVo loginByWx(String openId);

    /**
     * 更新用户信息
     * @param userId
     * @param userInfoDto
     * @return
     */
    void update(String userId, UserInfoDto userInfoDto);

    /**
     * 获取token
     * @param userId
     * @param secret
     * @return
     */
    String getToken(String userId, String secret);

    /**
     * 查询登陆日志
     * @param token
     * @return
     */
    LoginLog getByToken(String token);

}
