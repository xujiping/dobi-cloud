package com.cloud.base.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 活动报名工具类
 *
 * @author xujiping
 * @date 2019-08-02 11:22
 */
public class SignUtil {

    /**
     * 转换成json字符串
     *
     * @param covers
     * @param images
     * @return
     */
    public static String toJsonStr(String covers, String images) {
        JSONObject jsonObject = new JSONObject();
        if (StrUtil.isNotBlank(covers)) {
            jsonObject.put("covers", StrUtil.removeSuffix(covers, ","));
        }
        if (StrUtil.isNotBlank(images)) {
            jsonObject.put("images", StrUtil.removeSuffix(images, ","));
        }
        return jsonObject.toJSONString();
    }
}
