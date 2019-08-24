package com.cloud.fast.service;

import com.cloud.fast.entity.vo.UserOpenInfoVo;

/**
 * 用户中心相关接口服务
 * @Author: xujiping
 * @Date: 2019年8月24日 0024 下午 05:28:46
 * @Version 1.0
 */
public interface UserCenterService {

    /**
     * 获取用户开放信息
     * @param userId
     * @return
     */
    UserOpenInfoVo getOpenInfo(String userId);

}
