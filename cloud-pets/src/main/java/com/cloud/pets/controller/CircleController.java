package com.cloud.pets.controller;

import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Circle;
import com.cloud.pets.entity.vo.CircleTypeVo;
import com.cloud.pets.entity.vo.CircleVo;
import com.cloud.pets.service.CircleService;
import com.cloud.pets.service.CircleTypeService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 圈子 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@RestController
@RequestMapping("/circle")
@Validated
@Api(tags = "圈子")
public class CircleController {

    @Autowired
    private CircleService circleService;

    @Autowired
    private CircleTypeService circleTypeService;

    @PassToken
    @ApiOperation(value = "刷新圈子列表", httpMethod = "GET", response = Circle.class, notes =
            "上拉刷新历史数据，传当前数据最小时间戳之前的数据；下拉加载新数据，传当前数据最大时间戳之后的数据。")
    @GetMapping("list")
    public String list(@ApiParam(required = true, name = "size", value = "目标数据量")
                       @Pattern(regexp = "^\\d*[02468]$", message = "只能为偶数")
                       @RequestParam(value = "size") Integer size,
                       @ApiParam(required = true, name = "time", value = "取数据时间")
                       @RequestParam(value = "time") Long time,
                       @ApiParam(name = "before", value = "是否取时间之前的数据", defaultValue = "false")
                       @RequestParam(required = false, defaultValue = "false") boolean before,
                       @ApiParam(name = "circleTypeId", value = "圈子类型ID")
                       @RequestParam(required = false) Integer circleTypeId) {
        ReturnBean rb = new ReturnBean();
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("time", time);
        params.put("before", before);
        if (circleTypeId != null) {
            params.put("circleTypeId", circleTypeId);
        }
        SpageUtil<Circle> spageUtil = new SpageUtil<>();
        spageUtil.setStep(size);
        spageUtil = circleService.listByPage(params, spageUtil);
        List<Circle> rows = spageUtil.getRows();
        if (rows != null && rows.size() > 0) {
            // 按时间排序
            rows.sort(Comparator.comparing(Circle::getCreateTime));
            rb.setData(rows);
            rb.setMinTime(rows.get(0).getCreateTime().getTime());
            rb.setMaxTime(rows.get(rows.size() - 1).getCreateTime().getTime());
        }
        rb.setCount(spageUtil.getTotal());
        return rb.toJson();
    }

    @PassToken
    @ApiOperation(value = "刷新圈子列表", httpMethod = "GET", response = Circle.class, notes =
            "分页查询列表，按时间倒序")
    @GetMapping("list/page")
    public String listByPage(@ApiParam(name = "page", value = "页码")
                             @Min(1)
                             @RequestParam(required = false, defaultValue = "1") Integer page,
                             @ApiParam(required = true, name = "size", value = "目标数据量")
                             @Min(2)
                             @Max(20)
                             @RequestParam(value = "size") Integer size,
                             @ApiParam(name = "circleTypeId", value = "圈子类型ID")
                             @RequestParam(required = false) Integer circleTypeId) {
        com.cloud.base.constants.ReturnBean rb = new com.cloud.base.constants.ReturnBean();
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("page", page);
        if (circleTypeId != null) {
            params.put("circleTypeId", circleTypeId);
        }
        SpageUtil<Circle> spageUtil = new SpageUtil<>(page, size);
        spageUtil = circleService.listByPage(params, spageUtil);
        List<Circle> rows = spageUtil.getRows();
        List<CircleVo> circleVos;
        if (rows != null && rows.size() > 0) {
            circleVos = rows.stream().map(circle -> circleService.wrapper(circle)).collect(Collectors.toList());
            // 按时间排序
            circleVos.sort(Comparator.comparing(CircleVo::getCreateTime));
            rb.setData(circleVos);
            rb.setMinTime(circleVos.get(0).getCreateTime().getTime());
            rb.setMaxTime(circleVos.get(rows.size() - 1).getCreateTime().getTime());
        }
        rb.setCount(spageUtil.getTotal());
        return rb.toJson();
    }

    @PassToken
    @ApiOperation(value = "通过Id获取圈子详情", httpMethod = "GET", response = CircleVo.class, notes = "获取圈子详情")
    @GetMapping("/info/{id}")
    public String queryCircleById(@ApiParam(name = "id", value = "圈子ID", required = true)
                                  @PathVariable(value = "id") Long id) {
        com.cloud.base.constants.ReturnBean rb = new com.cloud.base.constants.ReturnBean();
        CircleVo circle = circleService.getOne(id);
        rb.setData(circle);
        return rb.toJson();
    }

    @PassToken
    @ApiOperation(value = "查询所有的圈子类型列表", httpMethod = "GET", response = CircleTypeVo.class, notes = "返回所有的圈子类型列表")
    @GetMapping("types")
    public String list() {
        ReturnBean rb = new ReturnBean();
        List<CircleTypeVo> all = circleTypeService.getAll();
        rb.setData(all);
        rb.setCount((long) all.size());
        return rb.toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "发布（待实现）", httpMethod = "GET", response = CircleTypeVo.class, notes = "发布")
    @GetMapping("publish")
    public String publish(@RequestParam String subject,
                          @RequestParam Integer typeId,
                          @RequestParam String title,
                          @RequestParam String content,
                          MultipartFile multipartFile) {
        ReturnBean rb = new ReturnBean();
        // todo 发布圈子
        return rb.toJson();
    }

}

