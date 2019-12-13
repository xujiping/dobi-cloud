package com.cloud.admin.fast.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.Carousel;
import com.cloud.admin.fast.service.CarouselService;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 轮播 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/carousel")
@Api(tags = "轮播图")
@ResponseResult
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @ApiOperation(value = "分页查询")
    @PassToken
    @GetMapping("page")
    public Page<Carousel> list(@ApiParam(name = "page", value = "页码", defaultValue = "1")
                               @RequestParam(required = false, defaultValue = "1") Integer page,
                               @ApiParam(name = "size", value = "大小", defaultValue = "10")
                               @RequestParam(required = false, defaultValue = "10") Integer size,
                               @ApiParam(required = true, name = "subject", value = "主题")
                               @RequestParam String subject) {
        Page<Carousel> pageObject = new Page<>(page, size);
        Map<String, Object> params = MapUtil.newHashMap(1);
        if (StrUtil.isNotBlank(subject)) {
            params.put("subject", subject);
        }
        pageObject.setCondition(params);
        return carouselService.listByPage(pageObject);
    }

}

