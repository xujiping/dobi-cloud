package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.pets.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
public interface UserService extends IService<User> {

    /**
     * 获取用户信息
     * @param username 用户名
     * @return User
     */
    User get(String username);

    /**
     * 更新用户信息
     * @param id 用户ID
     * @param nickname 昵称
     * @param age 年龄
     * @param sex 性别
     * @param phone 手机号
     * @param address 地址
     * @return
     */
    boolean update(Long id, String nickname, Integer age, Integer sex, String phone, String address);
}
