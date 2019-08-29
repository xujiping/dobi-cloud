package com.cloud.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.fast.entity.LyxRecommend;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.SignActivity;
import com.cloud.fast.entity.vo.LyxRecommendVo;
import com.cloud.fast.entity.vo.SignActivityVo;

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

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<LyxRecommendVo> listByPage(Page<LyxRecommend> page);

    /**
     * 包装
     * @param lyxRecommend
     * @return
     */
    LyxRecommendVo wrapper(LyxRecommend lyxRecommend);

}
