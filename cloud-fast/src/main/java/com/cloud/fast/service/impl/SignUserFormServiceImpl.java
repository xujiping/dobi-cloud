package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.SignUserForm;
import com.cloud.fast.entity.dto.SignUserFormDto;
import com.cloud.fast.mapper.SignUserFormMapper;
import com.cloud.fast.service.SignUserFormService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户报名表单信息 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-02
 */
@Service
public class SignUserFormServiceImpl extends ServiceImpl<SignUserFormMapper, SignUserForm> implements SignUserFormService {

    @Override
    public void join(String userId, SignUserFormDto signUserFormDto) {
        if (StrUtil.isBlank(userId)) {
            throw new BusinessException(ReturnCode.NEED_LOGIN);
        }
        if (signUserFormDto == null) {
            throw new BusinessException(ReturnCode.PARAMS_ERROR);
        }
        Long activityId = signUserFormDto.getActivityId();
        SignUserForm userActivity = getUserActivity(userId, activityId);
        if (userActivity != null) {
            throw new BusinessException(ReturnCode.USER_JOINED);
        }
        SignUserForm signUserForm = new SignUserForm();
        BeanUtils.copyProperties(signUserFormDto, signUserForm);
        signUserForm.setUserId(userId);
        boolean insert = insert(signUserForm);
        if (!insert) {
            throw new BusinessException(ReturnCode.FAIL);
        }
    }

    @Override
    public SignUserForm getUserActivity(String userId, Long activityId) {
        Wrapper<SignUserForm> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("activity_id", activityId);
        return selectOne(wrapper);
    }
}
