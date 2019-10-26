package com.cloud.pets.controller;

import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.constants.ReturnBean;
import com.cloud.pets.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-01-28
 */
@RestController
@RequestMapping("/collect")
@Api(tags = "收藏")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @UserLoginToken
    @ApiOperation(value = "收藏资源", httpMethod = "POST", response = ReturnBean.class, notes = "收藏资源")
    @PostMapping("resource")
    public String collect(HttpServletRequest request,
                          @ApiParam(required = true, name = "token", value = "用户Token")
                          @RequestHeader String token,
                          @ApiParam(required = true, name = "subject", value = "主题：circle圈子，demand需求")
                          @RequestParam String subject,
                          @ApiParam(required = true, name = "id", value = "资源ID")
                          @RequestParam Long id) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        ReturnBean rb = new ReturnBean();
        boolean add = collectService.add(key, subject, id);
        if (!add) {
            rb.setReturnCode(ResultCode.FAIL, null);
        }
        return rb.toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "取消收藏资源", httpMethod = "POST", response = ReturnBean.class, notes = "取消收藏资源")
    @PostMapping("cancel")
    public String cancle(HttpServletRequest request,
                         @ApiParam(required = true, name = "token", value = "用户Token")
                         @RequestHeader String token,
                         @ApiParam(required = true, name = "subject", value = "主题：circle圈子，demand需求")
                         @RequestParam String subject,
                         @ApiParam(required = true, name = "id", value = "资源ID")
                         @RequestParam Long id) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        return new ReturnBean(collectService.cancel(key, subject, id)).toJson();
    }

}

