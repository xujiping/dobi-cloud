package com.cloud.admin.controller;

import com.cloud.auth.jwt.UserLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台
 * @Author: xujiping
 * @Date: 2019年7月3日 0003 下午 04:35:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/platform")
@Api(tags = "平台")
@Validated
public class PlatformController {

}
