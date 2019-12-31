package com.cloud.admin.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
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

    /**
     * 查询
     * @param id
     * @return
     */
    GjCategory get(Integer id);

    /**
     * 新增
     * @param name
     * @param parentId
     * @return
     */
    GjCategory add(String name, String parentId);


    /**
     * 条件查询
     * @param pageObject
     * @return
     */
    Page<GjCategory> list(Page<GjCategory> pageObject);

}
