package com.cloud.admin.controller;

import com.cloud.admin.config.WxConfig;
import com.cloud.admin.entity.vo.UserVo;
import com.cloud.admin.service.OpenChannelService;
import com.cloud.admin.service.SysUserService;
import com.cloud.admin.wx.OauthByWxCode;
import com.cloud.admin.wx.WxService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
@Slf4j
public class WxController {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private WxService wxService;

    @Autowired private SysUserService userService;

    @PassToken
    @ApiOperation(value = "微信code2Session", httpMethod = "POST", notes = "成功之后返回用户token")
    @PostMapping("code2Session")
    public String code2Session(
            @ApiParam(required = true, name = "jsCode", value = "登录时获取的 code")
            @RequestParam String jsCode,
            @ApiParam(name = "appName", value = "应用名称")
            @RequestParam(required = false) String appName) {
        ReturnBean rb = new ReturnBean();
        OauthByWxCode oauthByWxCode = wxService.wxCodeToOauth(wxConfig, jsCode, appName);
        if (oauthByWxCode == null || oauthByWxCode.getErrcode() != null){
            rb.setReturnCode(ReturnCode.WX_CODE_SESSION_ERROR, null);
        }
        String openid = Objects.requireNonNull(oauthByWxCode).getOpenid();
        // 判断本地是否有用户，如果有则返回token，如果没有则创建新用户，并返回token
        UserVo userVo = userService.loginByWx(openid, appName);
        rb.setData(userVo);
        return rb.toJson();
    }
}
