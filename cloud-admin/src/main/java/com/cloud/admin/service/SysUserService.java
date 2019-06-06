package com.cloud.admin.service;

import com.cloud.admin.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.entity.vo.UserVo;

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

}
