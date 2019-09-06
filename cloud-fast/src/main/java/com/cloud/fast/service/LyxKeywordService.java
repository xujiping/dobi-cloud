package com.cloud.fast.service;

import com.cloud.fast.entity.LyxKeyword;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 关键词表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
public interface LyxKeywordService extends IService<LyxKeyword> {

    /**
     * 根据关键词查询
     * @param name
     * @return
     */
    LyxKeyword getByName(String name);

    /**
     * 更新数量
     * @param add
     */
    void updateCount(Long id, boolean add);

    /**
     * 批量更新数量
     * @param ids
     * @param add
     */
    void updateCount(String ids, boolean add);

    /**
     * 根据ID列表查询
     * @param ids
     * @return
     */
    List<LyxKeyword> listByIds(String ids);
}
