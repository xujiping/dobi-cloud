package com.cloud.pets.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.auth.jwt.UcHttpUtil;
import com.cloud.auth.jwt.UserCenterConfig;
import com.cloud.base.constants.ReturnBean;
import com.cloud.pets.entity.vo.UcUserVo;
import com.cloud.pets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public UcUserVo getFromUc(String token, UserCenterConfig userCenterConfig) {
        ReturnBean returnBean = UcHttpUtil.get(userCenterConfig.getRequestUser(), token, null);
        if (returnBean.isSuccess()){
            JSONObject userJson = (JSONObject) returnBean.getData();
            return JSON.toJavaObject(userJson, UcUserVo.class);
        }
        return null;
    }

}
