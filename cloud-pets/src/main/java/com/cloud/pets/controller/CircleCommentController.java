package com.cloud.pets.controller;


import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
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
 * 圈子评论 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-06-24
 */
@Controller
@RequestMapping("/circleComment")
public class CircleCommentController {

    @UserLoginToken
    @ApiOperation(value = "发布评论", httpMethod = "POST", response = ReturnBean.class, notes = "发布评论")
    @PostMapping("add")
    public String add(HttpServletRequest request,
                          @ApiParam(required = true, name = "token", value = "用户Token")
                          @RequestHeader String token,
                          @ApiParam(required = true, name = "subject", value = "主题：circle圈子，demand需求")
                          @RequestParam String subject,
                          @ApiParam(required = true, name = "id", value = "资源ID")
                          @RequestParam Long id,
                          @ApiParam(required = true, name = "content", value = "评论内容")
                          @RequestParam String content) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        ReturnBean rb = new ReturnBean();
        // todo 发布评论
        return rb.toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "更新状态", httpMethod = "POST", response = ReturnBean.class, notes = "更新状态")
    @PostMapping("status")
    public String updateStatus(HttpServletRequest request,
                          @ApiParam(required = true, name = "token", value = "用户Token")
                          @RequestHeader String token,
                          @ApiParam(required = true, name = "subject", value = "主题：circle圈子，demand需求")
                          @RequestParam String subject,
                          @ApiParam(required = true, name = "id", value = "资源ID")
                          @RequestParam Long id,
                          @ApiParam(required = true, name = "status", value = "状态")
                          @RequestParam String status) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        ReturnBean rb = new ReturnBean();
        // todo 更新状态
        return rb.toJson();
    }

}

