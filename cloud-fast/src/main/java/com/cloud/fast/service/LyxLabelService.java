package com.cloud.fast.service;

import com.cloud.fast.entity.LyxLabel;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.vo.LyxLabelVo;

import java.util.List;

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

    /**
     * 按ID查询
     * @param ids
     * @return
     */
    List<LyxLabel> listByIds(String ids);

    /**
     * 包装
     * @param lyxLabel
     * @param userId
     * @return
     */
    LyxLabelVo wrapper(LyxLabel lyxLabel, String userId);

    /**
     * 按用户查询
     * @param userId
     * @param subject
     * @return
     */
    List<LyxLabel> listByUserLike(String userId, String subject);
}
