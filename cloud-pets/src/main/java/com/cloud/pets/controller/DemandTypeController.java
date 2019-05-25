package com.cloud.pets.controller;


import com.cloud.base.constants.ReturnBean;
import com.cloud.pets.entity.DemandType;
import com.cloud.pets.service.DemandTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@RestController
@RequestMapping("/demandType")
public class DemandTypeController {

    @Autowired private DemandTypeService demandTypeService;

    @ApiOperation(value = "查询所有需求的类型列表", httpMethod = "GET", response = DemandType.class, notes = "查询所有需求的类型列表")
    @GetMapping("list")
    public String list(){
        ReturnBean rb = new ReturnBean();
        List<DemandType> all = demandTypeService.getAll();
        rb.setData(all);
        rb.setCount((long) all.size());
        return rb.toJson();
    }

}

