package com.cloud.pets.controller;

import com.cloud.pets.entity.Carousel;
import com.cloud.pets.entity.dto.CarouselDto;
import com.cloud.pets.service.CarouselService;
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
public class CarouselController {

    @Autowired private CarouselService carouselService;

    @ApiOperation(value = "获取轮播图列表", httpMethod = "GET", response = Carousel.class, notes = "返回轮播图列表")
    @GetMapping("list")
    public String getCarouselList(
            @ApiParam(required = true, name = "subject", value = "主题：home首页")
            @RequestParam String subject){
        com.cloud.base.constants.ReturnBean rb = new com.cloud.base.constants.ReturnBean();
        List<Carousel> carousels = carouselService.listBySubject(subject);
        List<CarouselDto> list = new ArrayList<>();
        if (carousels != null && carousels.size() > 0){
            list = carousels.stream().map(carousel -> carouselService.wrapper(carousel)).collect(Collectors.toList());
        }
        rb.setData(list);
        return rb.toJsonString();
    }

}

