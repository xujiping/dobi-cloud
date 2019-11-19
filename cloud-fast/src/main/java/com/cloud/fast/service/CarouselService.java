package com.cloud.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.fast.entity.Carousel;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 轮播 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-19
 */
public interface CarouselService extends IService<Carousel> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Carousel> listByPage(Page<Carousel> page);

}
