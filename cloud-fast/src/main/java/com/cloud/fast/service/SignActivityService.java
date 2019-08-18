package com.cloud.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.base.constants.ReturnCode;
import com.cloud.fast.entity.SignActivity;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.dto.SignActivityDto;
import com.cloud.fast.entity.vo.SignActivityDetailVo;
import com.cloud.fast.entity.vo.SignActivityVo;

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
     * 新增
     *
     * @param userId          用户ID
     * @param signActivityDto
     */
    SignActivity add(String userId, SignActivityDto signActivityDto);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<SignActivityVo> listByPage(Page<SignActivity> page);

    /**
     * 查询
     *
     * @param activityId
     * @return
     */
    SignActivity getById(String activityId);

    /**
     * 包装
     *
     * @param signActivity
     * @return
     */
    SignActivityVo wrapper(SignActivity signActivity);

    /**
     * 包装详情
     *
     * @param signActivity
     * @return
     */
    SignActivityDetailVo wrapperDetail(SignActivity signActivity, String userId);
}
