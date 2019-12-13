package com.cloud.admin.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.Carousel;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 轮播 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
public interface CarouselService extends IService<Carousel> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Carousel> listByPage(Page<Carousel> page);

}
