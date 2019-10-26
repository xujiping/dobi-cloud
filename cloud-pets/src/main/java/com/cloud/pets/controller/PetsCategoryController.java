package com.cloud.pets.controller;

import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.entity.PetsCategory;
import com.cloud.pets.service.PetsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 宠物类别表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@RestController
@RequestMapping("/petsCategory")
@Validated
@Api(tags = "宠物")
public class PetsCategoryController {

    @Autowired private PetsCategoryService petsCategoryService;

    @PassToken
    @ApiOperation(value = "查询所有的宠物类型列表", httpMethod = "GET", response = PetsCategory.class, notes = "返回所有的宠物类型列表")
    @GetMapping("list")
    public String list(){
        ReturnBean rb = new ReturnBean();
        List<PetsCategory> all = petsCategoryService.getAll();
        long total = all.size();
        rb.setData(all);
        rb.setCount(total);
        return rb.toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "新增宠物类别", httpMethod = "POST", response = ReturnBean.class, notes = "新增宠物类别")
    @PostMapping("info")
    public String add(
            @ApiParam(required = true, name = "name", value = "类别名称")
            @NotBlank
            @RequestParam(value = "name") String name){
        ReturnBean rb = new ReturnBean();
        boolean add = petsCategoryService.add(name);
        if (!add){
            throw new BusinessException(ResultCode.FAIL);
        }
        return rb.toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "更新宠物类别", httpMethod = "PUT", response = ReturnBean.class, notes = "更新名称、状态")
    @PutMapping("info/{id}")
    public String update(
            @ApiParam(required = true, name = "id", value = "ID")
            @PathVariable(value = "id") Integer id,
            @ApiParam(name = "name", value = "名称")
            @RequestParam(required =  false, value = "name") String name,
            @ApiParam(name = "status", value = "状态：pass、block")
            @RequestParam(required = false, value="status") String status){
        ReturnBean rb = new ReturnBean();
        boolean update = petsCategoryService.update(id, name, status);
        if (!update){
            throw new BusinessException(ResultCode.FAIL);
        }
        return rb.toJson();
    }

}

