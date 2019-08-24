package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.SignActivity;
import com.cloud.fast.entity.SignUserForm;
import com.cloud.fast.entity.dto.SignUserFormDto;
import com.cloud.fast.entity.vo.UserApplyVo;
import com.cloud.fast.mapper.SignUserFormMapper;
import com.cloud.fast.service.SignActivityService;
import com.cloud.fast.service.SignUserFormService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.fast.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private SignActivityService signActivityService;

    @Override
    public void join(String userId, SignUserFormDto signUserFormDto) {
        if (StrUtil.isBlank(userId)) {
            throw new BusinessException(ReturnCode.NEED_LOGIN);
        }
        if (signUserFormDto == null) {
            throw new BusinessException(ReturnCode.PARAMS_ERROR);
        }
        String activityId = signUserFormDto.getActivityId();
        SignActivity signActivity = signActivityService.selectById(activityId);
        if (signActivity == null) {
            throw new BusinessException(ReturnCode.ACTIVITY_NOT_ESIXT);
        }
        if (signActivity.getStatus() != Constants.STATUS_NORMAL) {
            throw new BusinessException(ReturnCode.ACTIVITY_END);
        }
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
    public SignUserForm getUserActivity(String userId, String activityId) {
        Wrapper<SignUserForm> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("activity_id", activityId);
        return selectOne(wrapper);
    }

    @Override
    public List<SignUserForm> listByUser(String userId) {
        if (StrUtil.isBlank(userId)) {
            return null;
        }
        Wrapper<SignUserForm> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        return selectList(wrapper);
    }

    @Override
    public UserApplyVo wrapper(SignUserForm signUserForm) {
        UserApplyVo applyVo = new UserApplyVo();
        if (signUserForm == null){
            return null;
        }
        BeanUtils.copyProperties(signUserForm, applyVo);
        String activityId = signUserForm.getActivityId();
        SignActivity signActivity = signActivityService.getById(activityId);
        if (signActivity != null){
            applyVo.setTitle(signActivity.getTitle());
            applyVo.setTime(TimeUtil.format(signActivity.getStartTime(), signActivity.getEndTime()));
            applyVo.setStatus(String.valueOf(signActivity.getStatus()));
        }else{
            return null;
        }
        applyVo.setJoined(true);
        return applyVo;
    }

    @Override
    public List<SignUserForm> listByActivity(String activityId) {
        if (StrUtil.isBlank(activityId)) {
            return null;
        }
        Wrapper<SignUserForm> wrapper = new EntityWrapper<>();
        wrapper.eq("activity_id", activityId);
        return selectList(wrapper);
    }
}
