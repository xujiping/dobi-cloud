package com.cloud.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.fast.entity.SignActivity;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.dto.SignActivityDto;

import java.util.Map;

/**
 * <p>
 * 活动报名表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-02
 */
public interface SignActivityService extends IService<SignActivity> {

    /**
     *  新增
     * @param userId 用户ID
     * @param signActivityDto
     */
    SignActivity add(String userId, SignActivityDto signActivityDto);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<SignActivity> listByPage(Page<SignActivity> page);

}
