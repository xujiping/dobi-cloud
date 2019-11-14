package com.cloud.fast.service;

import com.cloud.fast.entity.GjCategory;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 古籍-类别 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
public interface GjCategoryService extends IService<GjCategory> {

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
     * 根据名称查询
     * @param name
     * @return
     */
    GjCategory getByName(String name);

    /**
     * 条件查询
     * @param params
     * @return
     */
    List<GjCategory> list(Map<String, Object> params);
}
