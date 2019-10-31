package com.cloud.fast.controller;

import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.Result;
import com.cloud.base.constants.ReturnBean;
import com.cloud.fast.client.UserCenterClient;
import com.cloud.fast.service.LyxKeywordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 关键词表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
@RestController
@RequestMapping("/lyxKeyword")
@Api(tags = "留余香-关键词")
public class LyxKeywordController {

    @Autowired
    private LyxKeywordService keywordService;

    @Autowired
    private UserCenterClient userCenterClient;

    @PassToken
    @ApiOperation(value = "获取关键词ID", httpMethod = "GET", notes = "获取关键词ID，没有则新增")
    @GetMapping("")
    public ReturnBean addKeywords(@ApiParam(required = true, name = "keyword", value = "关键词")
                                  @NotBlank
                                  @RequestParam String keyword) {
        Result result = userCenterClient.userInfo(null);
        return new ReturnBean(keywordService.getByName(keyword));
    }

}

