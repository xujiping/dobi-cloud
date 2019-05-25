package com.cloud.pets.controller;


import com.cloud.base.constants.ReturnBean;
import com.cloud.pets.entity.CircleType;
import com.cloud.pets.service.CircleTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 圈子类型 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@RestController
@RequestMapping("/circleType")
public class CircleTypeController {

    @Autowired private CircleTypeService circleTypeService;

    @ApiOperation(value = "查询所有的圈子类型列表", httpMethod = "GET", response = CircleType.class, notes = "返回所有的圈子类型列表")
    @GetMapping("list")
    public String list(){
        ReturnBean rb = new ReturnBean();
        List<CircleType> all = circleTypeService.getAll();
        rb.setData(all);
        rb.setCount((long) all.size());
        return rb.toJson();
    }

}

