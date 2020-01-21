package com.cloud.admin.fast.service;

import com.cloud.admin.fast.entity.ScPoetry;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.fast.entity.vo.ScPoetryVo;

import java.util.List;

/**
 * <p>
 * 诗词 服务类
 * </p>
 *
 * @author xujiping
 * @since 2020-01-18
 */
public interface ScPoetryService extends IService<ScPoetry> {

    /**
     *
     * @param path
     * @return
     */
    Long json2Sc(String path);

    /**
     * 按标题查询
     * @param title
     * @return
     */
    List<ScPoetry> getByTitle(String title);

    /**
     * 包装
     * @param poetry
     * @return
     */
    ScPoetryVo wrapper(ScPoetry poetry);

}
