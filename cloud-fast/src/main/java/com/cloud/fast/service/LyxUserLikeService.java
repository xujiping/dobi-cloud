package com.cloud.fast.service;

import com.cloud.fast.entity.LyxUserLike;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户喜欢收藏的 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
public interface LyxUserLikeService extends IService<LyxUserLike> {

    /**
     * 喜欢/收藏
     * @param userId
     * @param labelId
     * @param keywords
     * @return
     */
    boolean like(String userId, Long labelId, String keywords);

    /**
     * 取消喜欢
     * @param userId
     * @param labelId
     * @return
     */
    boolean dislike(String userId, Long labelId);

    /**
     * 是否已经喜欢
     * @param userId
     * @param labelId
     * @return
     */
    boolean liked(String userId, Long labelId);

    /**
     * 根据用户查询
     * @param userId
     * @return
     */
    List<LyxUserLike> listByUser(String userId);

}
