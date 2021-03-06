package com.cloud.pets.common.wx;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.cloud.pets.config.WxConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 04:07:16
 * @Version 1.0
 */
@Service
@Slf4j
public class WxService {

    /**
     * auth.code2Session
     * @param wxConfig
     * @param wxCode
     * @return
     */
    public OauthByWxCode wxCodeToOauth(WxConfig wxConfig, String wxCode) {
        if (StrUtil.isBlank(wxCode)) {
            return null;
        }
        String appid = wxConfig.getAppid();
        String secret = wxConfig.getSecret();
        log.info("根据小程序前端登录code获取授权sessionkey，appid[" + appid + "]，appSecret[" + secret + "],wxCode[" + wxCode + "]...");
        try {
            Map<String, Object> params = MapUtil.newHashMap(4);
            params.put("appid", appid);
            params.put("secret", secret);
            params.put("grant_type", "authorization_code");
            params.put("js_code", wxCode);
            String response = HttpUtil.get(wxConfig.getSessionkeyUrl(), params);
            log.info("微信code2Session接口返回：" + response);
            if (StrUtil.isNotBlank(response)) {
                JSONObject json = JSONObject.parseObject(response);
                if (json == null || json.containsKey("errcode")) {
                    return null;
                }
                if (json.containsKey("openid") && json.containsKey("session_key")) {
                    OauthByWxCode oauthByWxCode = new OauthByWxCode();
                    oauthByWxCode.setOpenid(json.getString("openid"));
                    oauthByWxCode.setSessionKey(json.getString("session_key"));
                    return oauthByWxCode;
                }
            }
            return null;
        } catch (Exception e) {
            log.error("根据小程序前端登录code获取授权sessionkey出错：", e);
        }
        return null;
    }

}
