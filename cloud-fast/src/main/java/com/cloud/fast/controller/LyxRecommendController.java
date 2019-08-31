package com.cloud.fast.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.LyxRecommend;
import com.cloud.fast.entity.SignActivity;
import com.cloud.fast.service.LyxLabelService;
import com.cloud.fast.service.LyxRecommendService;
import com.cloud.fast.service.LyxUserLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * <p>
 * 推荐表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
@RestController
@RequestMapping("/lyxRecommend")
@Api(tags = "留余香")
@Validated
public class LyxRecommendController {

    @Autowired
    private LyxRecommendService lyxRecommendService;

    @Autowired
    private LyxLabelService lyxLabelService;

    @Autowired
    private LyxUserLikeService lyxUserLikeService;

    @UserLoginToken
    @ApiOperation(value = "新增推荐", httpMethod = "POST")
    @PostMapping("info")
    public ReturnBean add(HttpServletRequest request,
                          @ApiParam(required = true, name = "title", value = "标题")
                          @NotBlank
                          @RequestParam String title,
                          @ApiParam(required = true, name = "desc", value = "描述")
                          @RequestParam String desc,
                          @ApiParam(required = true, name = "labels", value = "标签JSON数组")
                          @NotBlank
                          @RequestParam String labels) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        LyxRecommend recommend = lyxRecommendService.add(key, title, desc);
        if (recommend == null) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        String labelIds = lyxLabelService.add(key, labels);
        if (StrUtil.isNotBlank(labelIds)) {
            recommend.setLabelIds(labelIds);
            boolean update = lyxRecommendService.updateById(recommend);
            if (!update) {
                throw new BusinessException(ReturnCode.FAIL);
            }
        }
        return new ReturnBean();
    }

    @UserLoginToken
    @ApiOperation(value = "喜欢", httpMethod = "POST")
    @PostMapping("like")
    public ReturnBean like(HttpServletRequest request,
                           @ApiParam(required = true, name = "labelId", value = "标签ID")
                           @NotBlank
                           @RequestParam String labelId,
                           @ApiParam(required = true, name = "keywords", value = "关键词ID列表")
                           @NotBlank
                           @RequestParam String keywords) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        boolean like = lyxUserLikeService.like(key, Long.valueOf(labelId), keywords);
        if (!like) {
            return new ReturnBean(ReturnCode.FAIL);
        }
        return new ReturnBean();
    }

    @UserLoginToken
    @ApiOperation(value = "取消喜欢", httpMethod = "POST")
    @PostMapping("dislike")
    public ReturnBean dislike(HttpServletRequest request,
                           @ApiParam(required = true, name = "labelId", value = "标签ID")
                           @NotBlank
                           @RequestParam String labelId) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        boolean dislike = lyxUserLikeService.dislike(key, Long.valueOf(labelId));
        if (!dislike) {
            return new ReturnBean(ReturnCode.FAIL);
        }
        return new ReturnBean();
    }

    @PassToken
    @ApiOperation(value = "列表", httpMethod = "GET")
    @GetMapping("list")
    public String list(@ApiParam(name = "page", value = "页码", defaultValue = "1")
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "大小", defaultValue = "10")
                       @RequestParam(required = false, defaultValue = "10") Integer size) {
        Page<LyxRecommend> pageObject = new Page<>(page, size);
        return new ReturnBean(lyxRecommendService.listByPage(pageObject)).toJson();
    }

}

