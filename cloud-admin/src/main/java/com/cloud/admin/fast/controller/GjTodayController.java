package com.cloud.admin.fast.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjToday;
import com.cloud.admin.fast.entity.vo.TodayVo;
import com.cloud.admin.fast.service.GjTodayService;
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

/**
 * <p>
 * 每日推送 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-12-26
 */
@RestController
@RequestMapping("/gjToday")
@Api(tags = "每日推送")
@ResponseResult
public class GjTodayController {

    @Autowired private GjTodayService todayService;

    @ApiOperation(value = "分页查询")
    @PassToken
    @GetMapping("page")
    public Page<TodayVo> page(@ApiParam(name = "page", value = "页码", defaultValue = "1")
                                     @RequestParam(required = false, defaultValue = "1") Integer page,
                              @ApiParam(name = "size", value = "大小", defaultValue = "10")
                                     @RequestParam(required = false, defaultValue = "10") Integer size){
        Page<GjToday> pageObject = new Page<>(page, size);
        return todayService.page(pageObject);
    }

}

