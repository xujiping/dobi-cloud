package com.cloud.admin.fast.service;

import com.cloud.admin.fast.entity.GjAuthor;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 古籍-作者 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
public interface GjAuthorService extends IService<GjAuthor> {

    /**
     * 获取或创建作者
     * @param name
     * @return
     */
    GjAuthor getOrAdd(String name);

}
