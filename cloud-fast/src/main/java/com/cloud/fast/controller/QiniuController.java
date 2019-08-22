package com.cloud.fast.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.open.qiniu.QiniuUploadUtil;
import com.cloud.fast.config.QiniuConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 七牛云控制器
 *
 * @Author: xujiping
 * @Date: 2019年8月22日 0022 下午 02:43:17
 * @Version 1.0
 */
@RequestMapping("qiniu")
@RestController
@Api(tags = "七牛云")
public class QiniuController {

    @Autowired
    private QiniuConfig qiniuConfig;

    @PassToken
    @ApiOperation(value = "获取上传凭证", httpMethod = "GET")
    @GetMapping("upToken")
    public String getUpToken() {
        String upToken = QiniuUploadUtil.getUpToken(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey(), qiniuConfig.getBucket());
        if (StrUtil.isBlank(upToken)) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        JSONObject json = new JSONObject();
        json.put("upToken", upToken);
        return json.toJSONString();
    }
}
