package com.cloud.pets.controller;

import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Pets;
import com.cloud.pets.entity.vo.PetsVo;
import com.cloud.pets.service.PetsService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 宠物表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@RestController
@RequestMapping("/pets")
@Api(tags = "宠物")
public class PetsController {

    @Autowired
    private PetsService petsService;

    @ApiOperation(value = "获取宠物列表", httpMethod = "GET", response = Pets.class, notes =
            "获取宠物列表")
    @GetMapping("list")
    public String list(
            @ApiParam(required = true, name = "key", value = "用户ID")
            @RequestHeader String key,
            @ApiParam(required = true, name = "size", value = "目标数据量")
            @Pattern(regexp = "^\\d*[02468]$", message = "只能为偶数")
            @RequestParam(required = false, value = "size", defaultValue = "10") Integer size,
            @ApiParam(name = "page", value = "页码")
            @RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "categoryId", value = "宠物类别ID")
            @RequestParam(required = false) Integer categoryId,
            @ApiParam(name = "speciesId", value = "宠物品种ID")
            @RequestParam(required = false) Integer speciesId) {
        ReturnBean rb = new ReturnBean();
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("key", key);
        if (categoryId != null) {
            params.put("categoryId", categoryId);
        }
        if (speciesId != null) {
            params.put("speciesId", speciesId);
        }
        SpageUtil<Pets> spageUtil = new SpageUtil<>(page, size);
        spageUtil = petsService.listByPage(params, spageUtil);
        List<Pets> rows = spageUtil.getRows();
        List<PetsVo> petsVos = new ArrayList<>();
        if (rows != null && rows.size() > 0) {
            petsVos = rows.stream().map(pets -> petsService.wrapper(pets)).collect(Collectors.toList());
        }
        rb.setData(petsVos);
        rb.setCount(spageUtil.getTotal());
        return rb.toJson();
    }

    @ApiOperation(value = "添加宠物", httpMethod = "POST", response = String.class, notes = "添加宠物信息")
    @PostMapping("")
    public String add(
            @ApiParam(required = true, name = "key", value = "用户ID")
            @RequestHeader String key,
            @ApiParam(required = true, name = "categoryId", value = "类别")
            @RequestParam Integer categoryId,
            @ApiParam(required = true, name = "speciesId", value = "品种")
            @RequestParam Integer speciesId,
            @ApiParam(required = true, name = "nickname", value = "昵称")
            @RequestParam String nickname,
            @ApiParam(required = true, name = "age", value = "年龄")
            @RequestParam Integer age,
            @ApiParam(required = true, name = "sex", value = "性别")
            @RequestParam Integer sex) {
        ReturnBean rb = new ReturnBean();
        boolean add = petsService.add(key, categoryId, speciesId, nickname, age, sex);
        if (!add) {
            rb.setReturnCode(ReturnCode.FAIL, null);
        }
        return rb.toJson();
    }

    @ApiOperation(value = "更新宠物", httpMethod = "POST", response = String.class, notes = "更新宠物信息")
    @PutMapping("{id}")
    public String update(
            @ApiParam(required = true, name = "key", value = "用户ID")
            @RequestHeader String key,
            @ApiParam(required = true, name = "id", value = "宠物ID")
            @PathVariable(value = "id") Long id,
            @ApiParam(name = "categoryId", value = "类别")
            @RequestParam(required = false) Integer categoryId,
            @ApiParam(name = "speciesId", value = "品种")
            @RequestParam(required = false) Integer speciesId,
            @ApiParam(name = "nickname", value = "昵称")
            @RequestParam(required = false) String nickname,
            @ApiParam(name = "age", value = "年龄")
            @RequestParam(required = false) Integer age,
            @ApiParam(name = "sex", value = "性别")
            @RequestParam(required = false) Integer sex) {
        ReturnBean rb = new ReturnBean();
        // 检查宠物是否存在
        Pets pets = petsService.get(key, id);
        if (pets == null) {
            throw new BusinessException(ReturnCode.PETS_NOT_EXISTS);
        }
        boolean add = petsService.updateInfo(id, categoryId, speciesId, nickname, age, sex);
        if (!add) {
            rb.setReturnCode(ReturnCode.FAIL, null);
        }
        return rb.toJson();
    }

}

