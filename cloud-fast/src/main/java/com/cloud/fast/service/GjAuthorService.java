package com.cloud.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.fast.entity.GjAuthor;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.dto.AuthorDto;

/**
 * <p>
 * 古籍-作者 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
public interface GjAuthorService extends IService<GjAuthor> {

    /**
     * 创建作者
     * @param authorDto
     * @return
     */
    GjAuthor newAuthor(AuthorDto authorDto);

    /**
     * 获取作者信息
     * @param authorId
     * @return
     */
    GjAuthor get(Long authorId);

    /**
     * 分页
     * @param pageObject
     * @return
     */
    Page<GjAuthor> page(Page<GjAuthor> pageObject);
}
