package com.cloud.fast.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.constants.StatusEnum;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.util.SignUtil;
import com.cloud.fast.entity.SignActivity;
import com.cloud.fast.entity.SignUserForm;
import com.cloud.fast.entity.dto.SignActivityDto;
import com.cloud.fast.entity.vo.SignActivityDetailVo;
import com.cloud.fast.entity.vo.SignActivityVo;
import com.cloud.fast.entity.vo.UserApplyVo;
import com.cloud.fast.entity.vo.UserOpenInfoVo;
import com.cloud.fast.mapper.SignActivityMapper;
import com.cloud.fast.service.SignActivityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.fast.service.SignUserFormService;
import com.cloud.fast.service.UserCenterService;
import com.cloud.fast.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 活动报名表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-02
 */
@Service
public class SignActivityServiceImpl extends ServiceImpl<SignActivityMapper, SignActivity> implements SignActivityService {

    @Autowired
    private SignUserFormService signUserFormService;

    @Autowired
    private UserCenterService userCenterService;

    @Override
    public SignActivity add(String userId, SignActivityDto signActivityDto) {
        if (signActivityDto == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        String startTime = signActivityDto.getStartTime();
        String endTime = signActivityDto.getEndTime();
        String signStartTime = signActivityDto.getSignStartTime();
        String signEndTime = signActivityDto.getSignEndTime();
        // todo 字段校验
        SignActivity signActivity = new SignActivity();
        BeanUtils.copyProperties(signActivityDto, signActivity);
        signActivity.setImages(SignUtil.toJsonStr(signActivityDto.getCovers(), signActivityDto.getImages()));
        signActivity.setUserId(userId);
        signActivity.setCreateBy(userId);
        signActivity.setId(IdUtil.fastSimpleUUID());
        if (StrUtil.isNotBlank(startTime)) {
            signActivity.setStartTime(DateUtil.parse(startTime));
        }
        if (StrUtil.isNotBlank(endTime)) {
            signActivity.setEndTime(DateUtil.parse(endTime));
        }
        if (StrUtil.isNotBlank(signStartTime)) {
            signActivity.setStartTime(DateUtil.parse(signStartTime));
        }
        if (StrUtil.isNotBlank(signEndTime)) {
            signActivity.setEndTime(DateUtil.parse(signEndTime));
        }
        boolean insert = insert(signActivity);
        if (insert) {
            return selectById(signActivity.getId());
        }
        return null;
    }

    @Override
    public Page<SignActivityVo> listByPage(Page<SignActivity> page) {
        Page<SignActivityVo> result = new Page<>();
        Wrapper<SignActivity> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_time desc");
        wrapper.eq("status", StatusEnum.NORMAL.getCode());
        Map<String, Object> condition = page.getCondition();
        wrapper.orderBy(String.valueOf(condition.get("sort")));
        if (condition.containsKey("latitude") && condition.containsKey("latitude")) {
            // todo 查询附近
        }
        page = selectPage(page, wrapper);
        List<SignActivity> records = page.getRecords();
        List<SignActivityVo> list;
        if (records != null && records.size() > 0) {
            result.setSize(page.getSize());
            result.setTotal(selectCount(wrapper));
            list = records.stream().map(this::wrapper).collect(Collectors.toList());
            result.setRecords(list);
        }
        return result;
    }

    @Override
    public SignActivity getById(String activityId) {
        if (activityId == null) {
            return null;
        }
        return selectById(activityId);
    }

    @Override
    public SignActivityVo wrapper(SignActivity signActivity) {
        SignActivityVo signActivityVo = new SignActivityVo();
        if (signActivity == null) {
            return signActivityVo;
        }
        BeanUtils.copyProperties(signActivity, signActivityVo);
        String images = signActivity.getImages();
        JSONObject json = JSONObject.parseObject(images);

        signActivityVo.setCovers(json.getString("covers"));
        signActivityVo.setImages(json.getString("images"));
        // 时间
        Date startTime = signActivity.getStartTime();
        Date endTime = signActivity.getEndTime();
        signActivityVo.setTime(TimeUtil.format(startTime, endTime));
        return signActivityVo;
    }

    @Override
    public SignActivityDetailVo wrapperDetail(SignActivity signActivity, String userId) {
        SignActivityDetailVo detailVo = new SignActivityDetailVo();
        if (signActivity == null) {
            return detailVo;
        }
        String id = signActivity.getId();
        Date startTime = signActivity.getStartTime();
        Date endTime = signActivity.getEndTime();
        String createBy = signActivity.getCreateBy();
        BeanUtils.copyProperties(signActivity, detailVo);
        detailVo.setTime(TimeUtil.format(startTime, endTime));
        detailVo.setCreateTime(DateUtil.formatDateTime(signActivity.getCreateTime()));
        // 用户相关
        if (StrUtil.isNotBlank(userId)) {
            SignUserForm signUserForm = signUserFormService.getUserActivity(userId, id);
            detailVo.setSigned(signUserForm != null);
        }
        // 发起人
        if (StrUtil.isNotBlank(createBy)){
            UserOpenInfoVo openInfo = userCenterService.getOpenInfo(createBy);
            detailVo.setCreateHeader(openInfo.getAvatar());
        }
        // 参与人员
        List<UserOpenInfoVo> openInfoVoList;
        List<SignUserForm> formList = signUserFormService.listByActivity(id);
        if (formList != null && formList.size() > 0) {
            openInfoVoList = formList.stream().map(form -> userCenterService.getOpenInfo(form.getUserId())).collect(Collectors.toList());
            detailVo.setSignUsers(openInfoVoList);
        }
        return detailVo;
    }

    @Override
    public List<SignActivity> listByUser(String userId) {
        if (StrUtil.isBlank(userId)) {
            return null;
        }
        Wrapper<SignActivity> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_time desc");
        wrapper.eq("user_id", userId);
        return selectList(wrapper);
    }

    @Override
    public UserApplyVo wrapperToApply(SignActivity signActivity) {
        UserApplyVo applyVo = new UserApplyVo();
        if (signActivity == null) {
            return applyVo;
        }
        BeanUtils.copyProperties(signActivity, applyVo);
        applyVo.setActivityId(signActivity.getId());
        applyVo.setTime(TimeUtil.format(signActivity.getStartTime(), signActivity.getEndTime()));
        applyVo.setStatus(signActivity.getStatus().toString());
        return applyVo;
    }

    @Override
    public void updateExpired() {
        Date now = new Date();
        Wrapper<SignActivity> wrapper = new EntityWrapper<>();
        wrapper.eq("status", StatusEnum.NORMAL.getCode());
        wrapper.lt("end_time", now);
        List<SignActivity> list = selectList(wrapper);
        if (list!= null && list.size() > 0){
            for (SignActivity signActivity : list) {
                signActivity.setUpdateTime(now);
                signActivity.setStatus(Convert.toByte(StatusEnum.APPLY_FINISH.getCode()));
                if (updateById(signActivity)){
                    // 更新用户参加的状态
                    signUserFormService.updateStatus(signActivity.getId(), null, StatusEnum.APPLY_FINISH);
                }
            }
        }

    }
}
