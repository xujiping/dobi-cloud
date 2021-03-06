package com.cloud.pets.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.entity.PetsSpecies;
import com.cloud.pets.entity.PetsSpeciesDetail;
import com.cloud.pets.entity.vo.PetsSpeciesVo;
import com.cloud.pets.service.PetsSpeciesDetailService;
import com.cloud.pets.service.PetsSpeciesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
@Api(tags = "宠物品种")
public class PetsSpeciesController {

    @Autowired
    private PetsSpeciesService petsSpeciesService;

    @Autowired
    private PetsSpeciesDetailService speciesDetailService;

    @PassToken
    @ApiOperation(value = "查询品种类型列表", httpMethod = "GET", response = PetsSpecies.class, notes = "查询品种类型列表")
    @GetMapping("list")
    public String list(@ApiParam(name = "size", value = "目标数据量")
                       @RequestParam(required = false, defaultValue = "10") Integer size,
                       @ApiParam(name = "page", value = "页码")
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @ApiParam(name = "categoryId", value = "类别ID")
                       @RequestParam(required = false) Integer categoryId) {
        ReturnBean rb = new ReturnBean();
        Page<PetsSpecies> pageObject = new Page<>(page, size);
        Map<String, Object> params = MapUtil.newHashMap(1);
        if (categoryId != null) {
            params.put("categoryId", categoryId);
        }
        pageObject = petsSpeciesService.list(pageObject, params);
        List<PetsSpecies> list = pageObject.getRecords();
        List<PetsSpeciesVo> petsSpeciesVos;
        if (list != null && list.size() > 0) {
            petsSpeciesVos = list.stream().map(petsSpecies -> petsSpeciesService.wrapper(petsSpecies)).collect(Collectors.toList());
            rb.setData(petsSpeciesVos);
            rb.setCount((long) list.size());
        }
        return rb.toJson();
    }

    @PassToken
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
            throw new BusinessException(ResultCode.FAIL);
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
            throw new BusinessException(ResultCode.FAIL);
        }
        return rb.toJson();
    }

    @PassToken
    @ApiOperation(value = "详情", httpMethod = "GET", response = ReturnBean.class)
    @GetMapping("detail/{id}")
    public String detail(@PathVariable Integer id) {
        ReturnBean rb = new ReturnBean();
        PetsSpeciesDetail detail = speciesDetailService.selectById(id);
        rb.setData(detail);
        return rb.toJson();
    }

}

