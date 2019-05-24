package com.cloud.pets.service;

/**
 * token服务
 * @author xujiping
 * @date 2018-12-29 18:14
 */
public interface TokenService {

    /**
     * 生成token
     * @param key 用户ID
     * @return token
     */
    String generate(String key);

}
