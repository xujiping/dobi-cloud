package com.cloud.admin.fast.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 简/繁工具类
 *
 * @Author: xujiping
 * @Date: 2020年1月21日 0021 下午 02:13:14
 * @Version 1.0
 */
public class JianFanUtil {

    private static final String url = "http://jisujfhzh.market.alicloudapi.com/fontconvert/convert";

    private static final String APPCODE = "61f35d7457884d34887585f656454f91";

    /**
     * 转换类型 2s转成简体 2t转成繁体 2h转成火星文
     */
    private static final String[] TYPES = {"2s", "2t", "2h"};

    /**
     * 繁体转简体
     *
     * @param fan
     * @return
     */
    public static String toJian(String fan) {
        if (StrUtil.isBlank(fan)) {
            return fan;
        }
        Map<String, Object> params = MapUtil.newHashMap(2);
        params.put("type", TYPES[0]);
        params.put("content", fan);
        //链式构建请求
        String res = HttpRequest.get(url)
                //头信息，多个头信息多次调用此方法即可
                .header("Authorization", "APPCODE " + APPCODE)
                //表单内容
                .form(params)
                .timeout(20000)
                .execute().body();
        if (StrUtil.isNotBlank(res)) {
            JSONObject response = JSONObject.parseObject(res);
            String status = response.getString("status");
            if ("0".equals(status)) {
                JSONObject result = response.getJSONObject("result");
                return result.getString("rcontent");
            }
        }
        return null;
    }
}
