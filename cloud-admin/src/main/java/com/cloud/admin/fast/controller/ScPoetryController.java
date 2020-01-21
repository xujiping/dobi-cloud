package com.cloud.admin.fast.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.ScPoetry;
import com.cloud.admin.fast.entity.ScPoetry;
import com.cloud.admin.fast.entity.vo.ScPoetryVo;
import com.cloud.admin.fast.service.ScPoetryService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 诗词 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2020-01-18
 */
@RequestMapping("/scPoetry")
@RestController
@Api(tags = "诗词")
@ResponseResult
public class ScPoetryController {

    @Autowired private ScPoetryService poetryService;

    @PassToken
    @ApiOperation(value = "读取诗词", httpMethod = "GET")
    @GetMapping("json2Sc")
    public Long json2Sc(@ApiParam(required = true, name = "path", value = "路径")
                        @RequestParam String path) {
        for (int i = 5; i < 255; i++) {
            path = "D:\\javaProjects\\github\\chinese-poetry\\json\\poet.song." + i * 1000 +".json";
            poetryService.json2Sc(path);
        }
        return 0L;
    }

    @PassToken
    @ApiOperation(value = "分页列表", httpMethod = "GET")
    @GetMapping("page")
    public Page<ScPoetry> page(@ApiParam(name = "currentPage", value = "当前页码")
                               @RequestParam(required = false, defaultValue = "1") Integer currentPage,
                               @ApiParam(name = "size", value = "大小")
                               @RequestParam(required = false, defaultValue = "20") Integer size) {
        Page<ScPoetry> page = new Page<>(currentPage, size);
        return poetryService.selectPage(page);
    }

    @PassToken
    @ApiOperation(value = "诗词详情", httpMethod = "GET")
    @GetMapping("info/{id}")
    public ScPoetryVo info(@ApiParam(required = true, name = "id", value = "主键ID")
                         @PathVariable(value = "id") String id) {
        return poetryService.wrapper(poetryService.selectById(id));
    }

}

