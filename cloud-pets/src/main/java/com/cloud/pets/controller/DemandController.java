package com.cloud.pets.controller;

import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Demand;
import com.cloud.pets.entity.DemandType;
import com.cloud.pets.entity.vo.DemandVo;
import com.cloud.pets.service.DemandService;
import com.cloud.pets.service.DemandTypeService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 需求表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@RestController
@RequestMapping("/demand")
@Validated
@Api(tags = "需求")
public class DemandController {

    @Autowired
    private DemandService demandService;

    @Autowired
    private DemandTypeService demandTypeService;

    @ApiOperation(value = "刷新需求列表", httpMethod = "GET", response = Demand.class, notes =
            "上拉刷新历史数据，传当前数据最小时间戳之前的数据；下拉加载新数据，传当前数据最大时间戳之后的数据。")
    @GetMapping("list")
    public String list(
            @ApiParam(required = true, name = "size", value = "目标数据量")
            @Pattern(regexp = "^\\d*[02468]$", message = "只能为偶数")
            @RequestParam(value = "size") Integer size,
            @ApiParam(required = true, name = "time", value = "取数据时间")
            @RequestParam(value = "time") Long time,
            @ApiParam(name = "before", value = "是否取时间之前的数据", defaultValue = "false")
            @RequestParam(required = false, value = "before", defaultValue = "false") boolean before,
            @ApiParam(name = "typeId", value = "需求类别ID")
            @RequestParam(required = false) Integer typeId,
            @ApiParam(name = "categoryId", value = "宠物类别ID")
            @RequestParam(required = false) Integer categoryId,
            @ApiParam(name = "speciesId", value = "宠物品种ID")
            @RequestParam(required = false) Integer speciesId) {
        ReturnBean rb = new ReturnBean();
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("time", time);
        params.put("before", before);
        if (typeId != null) {
            params.put("typeId", typeId);
        }
        if (categoryId != null) {
            params.put("categoryId", categoryId);
        }
        if (speciesId != null) {
            params.put("speciesId", speciesId);
        }
        SpageUtil<Demand> spageUtil = new SpageUtil<>();
        spageUtil.setStep(size);
        spageUtil = demandService.listByPage(params, spageUtil);
        List<Demand> rows = spageUtil.getRows();
        if (rows != null && rows.size() > 0) {
            rb.setData(rows);
        }
        rb.setCount(spageUtil.getTotal());
        return rb.toJson();
    }

    @ApiOperation(value = "刷新需求列表", httpMethod = "GET", response = Demand.class, notes =
            "分页列表")
    @GetMapping("list/page")
    public String listByPage(
            @ApiParam(name = "page", value = "页码")
            @Min(1)
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam(required = true, name = "size", value = "目标数据量")
            @Min(2)
            @Max(20)
            @RequestParam(value = "size") Integer size,
            @ApiParam(name = "typeId", value = "需求类别ID")
            @RequestParam(required = false) Integer typeId,
            @ApiParam(name = "categoryId", value = "宠物类别ID")
            @RequestParam(required = false) Integer categoryId,
            @ApiParam(name = "speciesId", value = "宠物品种ID")
            @RequestParam(required = false) Integer speciesId) {
        // todo 需要完善卖宠、领养、配种等实现
        ReturnBean rb = new ReturnBean();
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        if (typeId != null) {
            params.put("typeId", typeId);
        }
        if (categoryId != null) {
            params.put("categoryId", categoryId);
        }
        if (speciesId != null) {
            params.put("speciesId", speciesId);
        }
        SpageUtil<Demand> spageUtil = new SpageUtil<>(page, size);
        spageUtil = demandService.listByPage(params, spageUtil);
        List<Demand> rows = spageUtil.getRows();
        List<DemandVo> demandVos;
        if (rows != null && rows.size() > 0) {
            demandVos = rows.stream().map(demand -> demandService.wrapper(demand)).collect(Collectors.toList());
            rb.setData(demandVos);
        }
        rb.setCount(spageUtil.getTotal());
        return rb.toJson();
    }

    @ApiOperation(value = "发布需求", httpMethod = "POST", response = ReturnBean.class, notes = "发布需求")
    @PostMapping("info")
    public String add(HttpServletRequest request,
                      @ApiParam(required = true, name = "typeId", value = "发布类型ID")
                      @RequestParam Integer typeId,
                      @ApiParam(name = "age", value = "年龄，单位：月")
                      @RequestParam(required = false) Integer age,
                      @ApiParam(required = true, name = "price", value = "价格，多少以内，前端给出区间")
                      @RequestParam Double price,
                      @ApiParam(name = "sex", value = "性别")
                      @RequestParam(required = false) Integer sex,
                      @ApiParam(required = true, name = "categoryId", value = "类别")
                      @RequestParam Integer categoryId,
                      @ApiParam(required = true, name = "speciesId", value = "品种")
                      @RequestParam Integer speciesId,
                      @ApiParam(required = true, name = "content", value = "备注")
                      @RequestParam String content) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        ReturnBean rb = new ReturnBean();
        boolean add = demandService.add(Integer.valueOf(key), typeId, age, price, sex, categoryId, speciesId, content);
        if (!add) {
            throw new BusinessException(ResultCode.FAIL);
        }
        return rb.toJson();
    }

    @ApiOperation(value = "通过Id获取详情", httpMethod = "GET", response = DemandVo.class, notes = "获取详情")
    @GetMapping("/info/{id}")
    public String queryCircleById(@ApiParam(name = "id", value = "圈子ID", required = true)
                                  @PathVariable(value = "id") Long id) {
        ReturnBean rb = new ReturnBean();
        DemandVo demandVo = demandService.getOne(id);
        if (demandVo == null) {
            throw new BusinessException(ResultCode.NOT_EXISTS);
        }
        rb.setData(demandVo);
        return rb.toJson();
    }

    @ApiOperation(value = "查询所有需求的类型列表", httpMethod = "GET", response = DemandType.class, notes = "查询所有需求的类型列表")
    @GetMapping("types")
    public String list() {
        ReturnBean rb = new ReturnBean();
        List<DemandType> all = demandTypeService.getAll();
        rb.setData(all);
        rb.setCount((long) all.size());
        return rb.toJson();
    }

}

