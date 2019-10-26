package com.cloud.pets.controller;

import com.cloud.base.constants.ResultCode;
import com.cloud.base.constants.ReturnBean;
import com.cloud.pets.common.wx.OauthByWxCode;
import com.cloud.pets.common.wx.WxService;
import com.cloud.pets.config.WxConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信相关接口
 *
 * @Author: xujiping
 * @Date: 2019年6月5日 0005 下午 03:58:46
 * @Version 1.0
 */
@RestController
@RequestMapping("wx")
@Api(tags = "微信相关接口")
public class WxController {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private WxService wxService;

    @ApiOperation(value = "微信code2Session", httpMethod = "POST")
    @PostMapping("code2Session")
    public String code2Session(
            @ApiParam(required = true, name = "jsCode", value = "登录时获取的 code")
            @RequestParam String jsCode) {
        ReturnBean rb = new ReturnBean();
        OauthByWxCode oauthByWxCode = wxService.wxCodeToOauth(wxConfig, jsCode);
        if (oauthByWxCode == null){
            rb.setReturnCode(ResultCode.WX_CODE_SESSION_ERROR, null);
        }
        // todo 判断本地是否有用户，如果有则返回token，如果没有则创建新用户，并返回token
        return rb.toJson();
    }
}
