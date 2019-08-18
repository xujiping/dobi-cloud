package com.cloud.admin.service;

import com.cloud.admin.entity.LoginLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 登录日志表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-16
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 新增
     * @param token
     * @param secret
     * @param userId
     */
    void add(String token, String secret, String userId);

}
