package com.cloud.pets.controller;


import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.entity.PetsSpecies;
import com.cloud.pets.entity.vo.PetsSpeciesVo;
import com.cloud.pets.service.PetsSpeciesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 宠物品种表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@RestController
@RequestMapping("/petsSpecies")
public class PetsSpeciesController {

    @Autowired
    private PetsSpeciesService petsSpeciesService;

    @ApiOperation(value = "查询所有的品种类型列表", httpMethod = "GET", response = PetsSpecies.class, notes = "查询所有的品种类型列表")
    @GetMapping("list")
    public String list() {
        ReturnBean rb = new ReturnBean();
        List<PetsSpecies> all = petsSpeciesService.getAll();
        List<PetsSpeciesVo> petsSpeciesVos = new ArrayList<>();
        if (all != null && all.size() > 0) {
            petsSpeciesVos = all.stream().map(petsSpecies -> petsSpeciesService.wrapper(petsSpecies)).collect(Collectors.toList());
        }
        long total = all.size();
        rb.setData(petsSpeciesVos);
        rb.setCount(total);
        return rb.toJson();
    }

    @ApiOperation(value = "新增", httpMethod = "POST", response = ReturnBean.class, notes = "新增品种类别")
    @PostMapping("info")
    public String add(
            @ApiParam(required = true, name = "name", value = "品种名称")
            @NotBlank
            @RequestParam String name,
            @ApiParam(required = true, name = "categoryId", value = "类别ID")
            @RequestParam Integer categoryId,
            @ApiParam(name = "heat", value = "发情期时长，单位: 天")
            @RequestParam(required = false) Integer heat) {
        ReturnBean rb = new ReturnBean();
        boolean add = petsSpeciesService.add(name, categoryId, heat);
        if (!add) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return rb.toJson();
    }

    @ApiOperation(value = "更新", httpMethod = "PUT", response = ReturnBean.class, notes = "更新名称、类别ID，发情期天数、状态")
    @PutMapping("info/{id}")
    public String update(
            @ApiParam(required = true, name = "id", value = "ID")
            @PathVariable(value = "id") Integer id,
            @ApiParam(name = "name", value = "名称")
            @RequestParam(required = false, value = "name") String name,
            @ApiParam(required = true, name = "categoryId", value = "类别ID")
            @RequestParam Integer categoryId,
            @ApiParam(name = "heat", value = "发情期时长，单位: 天")
            @RequestParam(required = false) Integer heat,
            @ApiParam(name = "status", value = "状态：pass、block")
            @RequestParam(required = false, value = "status") String status) {
        ReturnBean rb = new ReturnBean();
        boolean update = petsSpeciesService.update(id, name, categoryId, heat, status);
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        return rb.toJson();
    }

}

