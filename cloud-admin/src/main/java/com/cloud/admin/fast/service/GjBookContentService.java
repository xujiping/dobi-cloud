package com.cloud.admin.fast.service;

import com.cloud.admin.fast.entity.GjBookContent;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.fast.entity.vo.BookContentVo;

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
    BookContentVo getByMenuId(Long menuId);

    /**
     * 包装
     * @param bookContent
     * @return
     */
    BookContentVo wrapper(GjBookContent bookContent);

    /**
     * 更新内容
     * @param menuId
     * @param content
     * @return
     */
    BookContentVo update(Long menuId, String content, String transText, String annotation);

    /**
     * 查询
     * @param menuId
     * @return
     */
    GjBookContent get(Long menuId);

}
