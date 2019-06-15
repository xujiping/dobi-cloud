package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.pets.entity.Carousel;
import com.cloud.pets.entity.vo.CarouselVo;

import java.util.List;

/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-01-25
 */
public interface CarouselService extends IService<Carousel> {

    /**
     * 查询轮播图列表
     * @param subject 主题
     * @return
     */
    List<Carousel> listBySubject(String subject);

    /**
     * 封装
     * @param carousel carousel
     * @return
     */
    CarouselVo wrapper(Carousel carousel);
}
