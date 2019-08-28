package com.cloud.fast.service;

import com.cloud.fast.entity.LyxRecommend;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 推荐表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
public interface LyxRecommendService extends IService<LyxRecommend> {

    /**
     * 新增
     * @param userId
     * @param title
     * @param desc
     * @return
     */
    LyxRecommend add(String userId, String title, String desc);

}
