package com.cloud.pets.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.pets.entity.Carousel;
import com.cloud.pets.entity.vo.CarouselVo;
import com.cloud.pets.mapper.CarouselMapper;
import com.cloud.pets.service.CarouselService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 轮播图 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-01-25
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    @Override
    public List<Carousel> listBySubject(String subject) {
        Wrapper<Carousel> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(subject)) {
            wrapper.eq("subject", subject);
        }
        wrapper.eq("status", com.cloud.base.constants.Constants.STAT_NORMAL);
        wrapper.orderBy("create_time desc");
        return selectList(wrapper);
    }

    @Override
    public CarouselVo wrapper(Carousel carousel) {
        CarouselVo carouselVo = new CarouselVo();
        if (carousel == null){
            return carouselVo;
        }
        String url = carousel.getUrl();
        String skip = carousel.getSkip();
        if (StringUtils.isNotEmpty(url)){
            JSONObject json = JSON.parseObject(url);
            carouselVo.setUrl(json.getString("url"));
        }
        carouselVo.setSkip(skip);
        return carouselVo;
    }
}
