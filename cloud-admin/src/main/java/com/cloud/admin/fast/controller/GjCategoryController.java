package com.cloud.admin.fast.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjCategory;
import com.cloud.admin.fast.service.GjCategoryService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * 古籍-类别 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@RestController
@ResponseResult
@RequestMapping("/gjCategory")
@Api(tags = "古籍-类别")
public class GjCategoryController {

    @Autowired
    private GjCategoryService categoryService;

    @PassToken
    @ApiOperation(value = "获取分类信息", httpMethod = "GET")
    @GetMapping("info/{id}")
    public GjCategory info(@PathVariable Integer id) {
        return categoryService.get(id);
    }

    @PassToken
    @ApiOperation(value = "新增类别", httpMethod = "POST")
    @PostMapping("new")
    public GjCategory add(@ApiParam(required = true, name = "name", value = "名称")
                          @RequestParam String name,
                          @ApiParam(name = "parentId", value = "上级ID")
                          @RequestParam(required = false) String parentId) {
        return categoryService.add(name, parentId);
    }

    @PassToken
    @ApiOperation(value = "获取分类列表", httpMethod = "GET")
    @GetMapping("list")
    public Page<GjCategory> list(@ApiParam(name = "page", value = "页码", defaultValue = "1")
                                 @RequestParam(required = false, defaultValue = "1") Integer page,
                                 @ApiParam(name = "size", value = "大小", defaultValue = "10")
                                 @RequestParam(required = false, defaultValue = "10") Integer size,
                                 @ApiParam(name = "level", value = "级别")
                                 @RequestParam(required = false) Integer level) {
        Page<GjCategory> pageObject = new Page<>(page, size);
        Map<String, Object> params = MapUtil.newHashMap(1);
        if (level != null) {
            params.put("level", level);
        }
        pageObject.setCondition(params);
        return categoryService.list(pageObject);
    }

}

