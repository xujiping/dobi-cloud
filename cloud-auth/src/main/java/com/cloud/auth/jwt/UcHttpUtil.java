package com.cloud.auth.jwt;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.base.constants.ReturnBean;

/**
 * 用户中心请求工具类
 *
 * @Author: xujiping
 * @Date: 2019年6月6日 0006 下午 05:38:02
 * @Version 1.0
 */
public class UcHttpUtil {

    public static ReturnBean get(String url) {
        String userResponse = HttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(userResponse);
        return JSON.toJavaObject(jsonObject, ReturnBean.class);
    }
}
