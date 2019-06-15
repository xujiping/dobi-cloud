package com.cloud.pets.controller;

import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ReturnBean;
import com.cloud.pets.entity.Carousel;
import com.cloud.pets.entity.vo.CarouselVo;
import com.cloud.pets.service.CarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 轮播图 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-01-25
 */
@RestController
@RequestMapping("/carousel")
@Api(tags = "轮播图")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @PassToken
    @ApiOperation(value = "获取轮播图列表", httpMethod = "GET", response = Carousel.class, notes = "返回轮播图列表")
    @GetMapping("list")
    public String getCarouselList(@ApiParam(required = true, name = "subject", value = "主题：home首页")
                                  @RequestParam String subject) {
        ReturnBean rb = new ReturnBean();
        List<Carousel> carousels = carouselService.listBySubject(subject);
        List<CarouselVo> list = new ArrayList<>();
        if (carousels != null && carousels.size() > 0) {
            list = carousels.stream().map(carousel -> carouselService.wrapper(carousel)).collect(Collectors.toList());
        }
        rb.setData(list);
        return rb.toJson();
    }

}

