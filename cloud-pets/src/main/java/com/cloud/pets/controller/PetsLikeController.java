package com.cloud.pets.controller;


import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 点赞表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-06-24
 */
@Controller
@RequestMapping("/petsLike")
@Api(tags = "点赞")
public class PetsLikeController {

    @UserLoginToken
    @ApiOperation(value = "点赞（待实现）", httpMethod = "POST", response = ReturnBean.class, notes = "点赞")
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
        // todo 点赞
        return rb.toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "取消点赞（待实现）", httpMethod = "POST", response = ReturnBean.class, notes = "取消点赞")
    @PostMapping("cancel")
    public String cancle(HttpServletRequest request,
                         @ApiParam(required = true, name = "token", value = "用户Token")
                         @RequestHeader String token,
                         @ApiParam(required = true, name = "subject", value = "主题：circle圈子，demand需求")
                         @RequestParam String subject,
                         @ApiParam(required = true, name = "id", value = "资源ID")
                         @RequestParam Long id) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        // todo 取消点赞
        return new ReturnBean().toJson();
    }

}

