package com.cloud.fast.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.GjLink;

import java.util.List;

/**
 * <p>
 * 古籍-链接 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
public interface GjLinkService extends IService<GjLink> {

    /**
     * 列表
     * @param bookId
     * @return
     */
    List<GjLink> listByBookId(Long bookId);

}
