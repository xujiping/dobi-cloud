package com.cloud.pets.service;

import com.cloud.auth.jwt.UserCenterConfig;
import com.cloud.pets.entity.vo.UcUserVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
public interface UserService{

    /**
     * 从用户中心获取用户信息
     *
     * @param token
     * @return
     */
    UcUserVo getFromUc(String token, UserCenterConfig userCenterConfig);

}
