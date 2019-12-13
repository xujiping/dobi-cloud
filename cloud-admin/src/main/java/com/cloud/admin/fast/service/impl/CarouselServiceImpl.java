package com.cloud.admin.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.Carousel;
import com.cloud.admin.fast.mapper.CarouselMapper;
import com.cloud.admin.fast.service.CarouselService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 轮播 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    @Override
    public Page<Carousel> listByPage(Page<Carousel> page) {
        if (page == null) {
            return new Page<>();
        }
        Map<String, Object> condition = page.getCondition();
        Wrapper<Carousel> wrapper = new EntityWrapper<>();
        if (condition.containsKey("subject")) {
            wrapper.eq("subject", condition.get("subject"));
        }
        return selectPage(page);
    }
}
