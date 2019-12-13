package com.cloud.admin.fast.service;

import com.cloud.admin.fast.entity.GjBookContent;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 古籍-书籍内容 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
public interface GjBookContentService extends IService<GjBookContent> {

    /**
     * 根据菜单查询书籍内容
     * @param menuId
     * @return
     */
    GjBookContent getByMenuId(Long menuId);

}
