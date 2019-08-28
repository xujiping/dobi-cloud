package com.cloud.fast.service;

import com.cloud.fast.entity.LyxLabel;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 推荐标签表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
public interface LyxLabelService extends IService<LyxLabel> {

    /**
     * 新增标签
     * @param userId
     * @param labels
     * @return
     */
    String add(String userId, String labels);

}
