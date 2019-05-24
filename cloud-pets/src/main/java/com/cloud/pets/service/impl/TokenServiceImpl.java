package com.cloud.pets.service.impl;

import com.cloud.base.constants.Constants;
import com.cloud.base.util.TokenUtil;
import com.cloud.pets.service.RedisService;
import com.cloud.pets.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujiping
 * @date 2018-12-29 18:26
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    RedisService redisService;

    @Override
    public String generate(String key) {
        // 删除旧的token
        redisService.hmDelete(Constants.REDIS_USER_TOKEN, key);
        //保存到redis
        String token = TokenUtil.generate(key, System.currentTimeMillis());
        redisService.hmSet(Constants.REDIS_USER_TOKEN, key, token);
        //todo token保存到数据库
        return token;
    }
}
