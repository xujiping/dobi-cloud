package com.cloud.admin.fast.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.ScAuthor;
import com.cloud.admin.fast.service.ScAuthorService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 诗词-作者 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2020-01-18
 */
@RequestMapping("/scAuthor")
@RestController
@Api(tags = "诗词-作者")
@ResponseResult
public class ScAuthorController {

    @Autowired
    private ScAuthorService scAuthorService;

    @PassToken
    @ApiOperation(value = "读取作者", httpMethod = "GET")
    @GetMapping("json2Sc")
    public Long json2Sc(@ApiParam(required = true, name = "path", value = "路径")
                        @RequestParam String path) {
        return scAuthorService.json2Sc(path);
    }

    @PassToken
    @ApiOperation(value = "分页列表", httpMethod = "GET")
    @GetMapping("page")
    public Page<ScAuthor> page(@ApiParam(name = "currentPage", value = "当前页码")
                               @RequestParam(required = false, defaultValue = "1") Integer currentPage,
                               @ApiParam(name = "size", value = "大小")
                               @RequestParam(required = false, defaultValue = "20") Integer size) {
        Page<ScAuthor> page = new Page<>(currentPage, size);
        return scAuthorService.selectPage(page);
    }

    @PassToken
    @ApiOperation(value = "作者详情", httpMethod = "GET")
    @GetMapping("info")
    public ScAuthor info(@ApiParam(required = true, name = "name", value = "姓名")
                         @RequestParam String name) {
        return scAuthorService.getByName(name);
    }

}

