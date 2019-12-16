package com.cloud.admin.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjBookMenu;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.fast.entity.dto.MenuContentDto;

import java.util.List;

/**
 * <p>
 * 古籍-书籍目录表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
public interface GjBookMenuService extends IService<GjBookMenu> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<GjBookMenu> page(Page<GjBookMenu> page);

    /**
     * 新增菜单和内容
     * @param menuContentDto
     * @return
     */
    boolean addMenuAndContent(MenuContentDto menuContentDto);

    /**
     * 根绝书籍ID或标题查询目录
     * @param bookId
     * @return
     */
    List<GjBookMenu> getByBook(Long bookId, String title);

}
