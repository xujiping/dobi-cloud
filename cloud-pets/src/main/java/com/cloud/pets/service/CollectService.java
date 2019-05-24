package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.pets.entity.Collect;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-01-28
 */
public interface CollectService extends IService<Collect> {

    /**
     * 收藏
     * @param userId 用户ID
     * @param subject 主题
     * @param resouceId 资源ID
     * @return boolean
     */
    boolean add(String userId, String subject, long resouceId);

}
