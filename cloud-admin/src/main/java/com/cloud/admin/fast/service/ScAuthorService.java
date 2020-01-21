package com.cloud.admin.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.ScAuthor;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 诗词-作者 服务类
 * </p>
 *
 * @author xujiping
 * @since 2020-01-18
 */
public interface ScAuthorService extends IService<ScAuthor> {

    /**
     * json 2 诗词
     * @param path
     * @return
     */
    Long json2Sc(String path);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    ScAuthor getByName(String name);

}
