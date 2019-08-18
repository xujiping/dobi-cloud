package com.cloud.admin.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.cloud.admin.entity.LoginLog;
import com.cloud.admin.mapper.LoginLogMapper;
import com.cloud.admin.service.LoginLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.util.MD5Util;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-16
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public void add(String token, String secret, String userId) {
        if (StrUtil.isBlank(token) || StrUtil.isBlank(secret) || StrUtil.isBlank(userId)){
            return;
        }
        LoginLog loginLog = new LoginLog();
        loginLog.setToken(token);
        loginLog.setSecret(MD5Util.MD5(secret));
        loginLog.setUserId(userId);
        loginLog.setExpireTime(DateUtil.offsetDay(new DateTime(), 1));
        insert(loginLog);
    }
}
