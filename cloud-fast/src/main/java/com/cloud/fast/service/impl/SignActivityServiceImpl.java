package com.cloud.fast.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.util.SignUtil;
import com.cloud.fast.entity.SignActivity;
import com.cloud.fast.entity.dto.SignActivityDto;
import com.cloud.fast.mapper.SignActivityMapper;
import com.cloud.fast.service.SignActivityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public SignActivity add(String userId, SignActivityDto signActivityDto) {
        if (signActivityDto == null) {
            throw new BusinessException(ReturnCode.PARAMS_ERROR);
        }
        // todo 字段校验
        SignActivity signActivity = new SignActivity();
        BeanUtils.copyProperties(signActivityDto, signActivity);
        signActivity.setImages(SignUtil.toJsonStr(signActivityDto.getCovers(), signActivityDto.getImages()));
        signActivity.setUserId(userId);
        signActivity.setCreateBy(userId);
        signActivity.setId(IdUtil.fastSimpleUUID());
        boolean insert = insert(signActivity);
        if (insert) {
            return selectById(signActivity.getId());
        }
        return null;
    }

    @Override
    public Page<SignActivity> listByPage(Page<SignActivity> page) {
        // todo 分页查询
        Wrapper<SignActivity> wrapper = new EntityWrapper<>();
        Map<String, Object> condition = page.getCondition();
        wrapper.orderBy(String.valueOf(condition.get("sort")));
        if (condition.containsKey("latitude") && condition.containsKey("latitude")) {
            // todo 查询附近
        }
        page = selectPage(page, wrapper);
        page.setTotal(selectCount(wrapper));
        return page;
    }
}
