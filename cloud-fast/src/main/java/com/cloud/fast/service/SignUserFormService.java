package com.cloud.fast.service;

import com.cloud.fast.entity.SignUserForm;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.dto.SignUserFormDto;
import com.cloud.fast.entity.vo.UserApplyVo;

import java.util.List;

/**
 * <p>
 * 用户报名表单信息 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-02
 */
public interface SignUserFormService extends IService<SignUserForm> {

    /**
     * 参加活动
     * @param userId 用户ID
     * @param signUserFormDto 报名实体
     */
    void join(String userId, SignUserFormDto signUserFormDto);

    /**
     * 查询用户参加的活动信息
     * @param userId
     * @param activityId
     * @return
     */
    SignUserForm getUserActivity(String userId, String activityId);

    /**
     * 获取用户参加的活动列表
     * @param userId
     * @return
     */
    List<SignUserForm> listByUser(String userId);

    /**
     * 包装
     * @param signUserForm
     * @return
     */
    UserApplyVo wrapper(SignUserForm signUserForm);

    /**
     * 获取活动的参加人员
     * @param activityId
     * @return
     */
    List<SignUserForm> listByActivity(String activityId);
}
