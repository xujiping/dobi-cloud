package com.cloud.fast.util;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 身份证工具类
 *
 * @Author: xujiping
 * @Date: 2019年9月19日 0019 下午 04:23:04
 * @Version 1.0
 */
@Slf4j
public class IdCardUtil {

    /**
     * 身份证校验url
     */
    private static final String ID_CARD_CHECK_URL = "http://checkid.market.alicloudapi.com/IDCard?name=";

    /**
     * 身份APPCODE
     */
    private static final String APPCODE = "8f33500f770b4df3b3569bb55b3f8504";

    /**
     * 实名校验
     * 成功返回：{"status":"01","msg":"实名认证通过！","idCard":"422129199209070536","name":"徐继平","sex":"男","area":"湖北省黄冈地区广济县","province":"湖北省","city":"黄冈地区","prefecture":"广济县","birthday":"1992-09-07","addrCode":"422129","lastCode":"6"}
     * 失败返回：{"status":"02","msg":"实名认证不通过！","idCard":"422129199209070536","name":"徐极品","sex":"","area":"","province":"","city":"","prefecture":"","birthday":"","addrCode":"","lastCode":""}
     *
     * @param realName
     * @param idCard
     * @return
     */
    public static JSONObject check(String realName, String idCard) {
        try {
            // 校验字段是否合法
            if (StrUtil.isBlank(realName) || StrUtil.isBlank(idCard)) {
                return null;
            }
            if (!IdcardUtil.isValidCard(idCard)) {
                return null;
            }
            String url = ID_CARD_CHECK_URL + realName + "&idCard=" + idCard;
            String result = HttpRequest.get(url)
                    //头信息，多个头信息多次调用此方法即可
                    .header("Authorization", "APPCODE " + APPCODE)
                    .timeout(20000)
                    .execute().body();
            log.debug("身份证实名校验：" + result);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error("身份证实名校验失败：", e);
            return null;
        }
    }

    public static void main(String[] args) {
        JSONObject check = check("徐继平", "422129199209070536");
        if (check == null || !check.getString("status").equals("01")) {
            System.out.println("不符合");
        }
    }
}
