package com.cloud.pets.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.service.TokenService;
import com.cloud.pets.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * 登陆、注册控制器
 *
 * @author xujiping
 * @date 2018-12-29 17:43
 */
@RequestMapping("")
@RestController
@Validated
@Slf4j
public class SignController {

    /**
     * 当需要身份认证的时候，跳转过来
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/authentication/require")
    public String requireAuthenication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReturnBean rb = new ReturnBean();
        String userAgent = request.getHeader("user-agent");
        log.info("User-Agent：{}", userAgent);
        rb.setReturnCode(ReturnCode.NEED_LOGIN, null);
        return rb.toJson();
    }

}
