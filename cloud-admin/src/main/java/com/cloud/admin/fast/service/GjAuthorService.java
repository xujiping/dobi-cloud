package com.cloud.admin.fast.service;

import com.cloud.admin.fast.entity.GjAuthor;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.fast.entity.dto.AuthorDto;

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
     * 创建作者
     * @param authorDto
     * @return
     */
    GjAuthor newAuthor(AuthorDto authorDto);

    /**
     * 获取或创建作者
     * @param name
     * @return
     */
    GjAuthor getOrAdd(String name);


    /**
     * 获取作者信息
     * @param authorId
     * @return
     */
    GjAuthor get(Long authorId);

}
