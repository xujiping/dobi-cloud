package com.cloud.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.SignForm;
import com.cloud.fast.entity.dto.SignFormDto;
import com.cloud.fast.mapper.SignFormMapper;
import com.cloud.fast.service.SignFormService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 报名表单 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-02
 */
@Service
public class SignFormServiceImpl extends ServiceImpl<SignFormMapper, SignForm> implements SignFormService {

    @Override
    public int getFormId(SignFormDto signFormDto) {
        if (signFormDto == null) {
            throw new BusinessException(ReturnCode.PARAMS_ERROR);
        }
        Wrapper<SignForm> wrapper = new EntityWrapper<>();
        wrapper.eq("name", signFormDto.getName());
        wrapper.eq("phone", signFormDto.getPhone());
        wrapper.eq("age", signFormDto.getAge());
        wrapper.eq("sex", signFormDto.getSex());
        SignForm signForm = selectOne(wrapper);
        if (signForm == null) {
            signForm = new SignForm();
            BeanUtils.copyProperties(signFormDto, signForm);
            boolean insert = insert(signForm);
            if (!insert) {
                throw new BusinessException(ReturnCode.FAIL);
            }
        }
        return signForm.getId();
    }
}
