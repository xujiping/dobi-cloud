package com.cloud.fast.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置
 *
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 04:00:39
 * @Version 1.0
 */
@Configuration
@Data
public class WxConfig {

    /**
     * 通过code向微信获取sessionkey
     */
    private String sessionkeyUrl = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;
}
