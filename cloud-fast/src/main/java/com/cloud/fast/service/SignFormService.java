package com.cloud.fast.service;

import com.cloud.fast.entity.SignForm;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.dto.SignFormDto;

/**
 * <p>
 * 报名表单 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-02
 */
public interface SignFormService extends IService<SignForm> {

    /**
     * 获取表单ID，如果没有则新增并返回
     * @param signFormDto
     * @return
     */
    int getFormId(SignFormDto signFormDto);

}
