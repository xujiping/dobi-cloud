package com.cloud.fast.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.auth.jwt.PassToken;
import com.cloud.fast.entity.GjCategory;
import com.cloud.fast.service.GjCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 古籍-类别 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@RestController
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
    public List<GjCategory> list(@ApiParam(name = "level", value = "级别")
                                 @RequestParam(required = false) Integer level) {
        Map<String, Object> params = MapUtil.newHashMap(1);
        params.put("level", level);
        return categoryService.list(params);
    }


}

