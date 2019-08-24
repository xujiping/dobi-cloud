package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.cloud.auth.jwt.UcHttpUtil;
import com.cloud.auth.jwt.UserCenterConfig;
import com.cloud.base.constants.ReturnBean;
import com.cloud.fast.entity.vo.UserOpenInfoVo;
import com.cloud.fast.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xujiping
 * @Date: 2019年8月24日 0024 下午 05:30:09
 * @Version 1.0
 */
@Service
public class UserCenterServiceImpl implements UserCenterService {

    @Autowired private UserCenterConfig userCenterConfig;

    @Override
    public UserOpenInfoVo getOpenInfo(String userId) {
        UserOpenInfoVo openInfoVo = new UserOpenInfoVo();
        if (StrUtil.isBlank(userId)){
            return openInfoVo;
        }
        openInfoVo.setUserId(userId);
        ReturnBean returnBean = UcHttpUtil.get(userCenterConfig.getRequestUserOpenInfo() + userId, null, null);
        if (returnBean.isSuccess()){
            JSONObject userJson = (JSONObject) returnBean.getData();
            if (userJson != null && userJson.containsKey("avatar")){
                openInfoVo.setAvatar(userJson.getString("avatar"));
            }
            if (userJson != null && userJson.containsKey("nickname")){
                openInfoVo.setNickname(userJson.getString("nickname"));
            }
        }
        return openInfoVo;
    }
}
