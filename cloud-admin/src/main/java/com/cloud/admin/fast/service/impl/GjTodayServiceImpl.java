package com.cloud.admin.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjToday;
import com.cloud.admin.fast.mapper.GjTodayMapper;
import com.cloud.admin.fast.service.GjTodayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 每日推送 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-26
 */
@Service
public class GjTodayServiceImpl extends ServiceImpl<GjTodayMapper, GjToday> implements GjTodayService {

    @Override
    public Page<GjToday> page(Page<GjToday> pageObject) {
        Wrapper<GjToday> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_tiome", false);
        return selectPage(pageObject);
    }
}
