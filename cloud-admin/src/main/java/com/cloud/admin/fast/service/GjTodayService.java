package com.cloud.admin.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjToday;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.fast.entity.vo.TodayVo;

/**
 * <p>
 * 每日推送 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-26
 */
public interface GjTodayService extends IService<GjToday> {

    /**
     * 分页查询
     * @param pageObject
     * @return
     */
    Page<TodayVo> page(Page<GjToday> pageObject);

    TodayVo wrapper(GjToday gjToday);
}
