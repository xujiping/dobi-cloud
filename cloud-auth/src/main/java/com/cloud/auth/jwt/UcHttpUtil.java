package com.cloud.auth.jwt;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnBean;

import java.util.Map;

/**
 * 用户中心请求工具类
 *
 * @Author: xujiping
 * @Date: 2019年6月6日 0006 下午 05:38:02
 * @Version 1.0
 */
public class UcHttpUtil {

    public static ReturnBean get(String url, String token, String accountId, Map<String, Object> params) {
        String userResponse = HttpRequest.get(url)
                .header(Constants.HEADER_TOKEN, token)
                .header(Constants.HEADER_ACCOUNT_ID, accountId)
                .form(params)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        JSONObject jsonObject = JSONObject.parseObject(userResponse);
        return JSON.toJavaObject(jsonObject, ReturnBean.class);
    }
}
