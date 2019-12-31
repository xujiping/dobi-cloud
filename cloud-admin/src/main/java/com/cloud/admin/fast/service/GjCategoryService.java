package com.cloud.admin.fast.service;

import com.cloud.admin.fast.entity.GjCategory;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 古籍-类别 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
public interface GjCategoryService extends IService<GjCategory> {

    /**
     * 获取或创建菜单
     * @param name
     * @param parentId
     * @return
     */
    GjCategory getOrAdd(String name, String parentId);

    /**
     * 获取菜单
     * @param name
     * @return
     */
    GjCategory getByName(String name);

}
