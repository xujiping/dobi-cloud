package com.cloud.admin.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjBook;
import com.cloud.admin.fast.entity.GjBookMenu;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.fast.entity.dto.MenuContentDto;
import com.cloud.admin.fast.entity.vo.BookContentVo;

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
     *
     * @param page
     * @return
     */
    Page<GjBookMenu> page(Page<GjBookMenu> page);

    /**
     * 新增菜单和内容
     *
     * @param menuContentDto
     * @return
     */
    boolean addMenuAndContent(MenuContentDto menuContentDto);

    /**
     * 根绝书籍ID或标题查询目录
     *
     * @param bookId
     * @return
     */
    List<GjBookMenu> getByBook(Long bookId, String title);

    /**
     * 解析一个html页面
     *
     * @param url
     * @return
     */
    boolean readHtml(String url, MenuContentDto menuContentDto);

    /**
     * 根据权重查询菜单
     *
     * @param bookId
     * @param weight
     * @return
     */
    GjBookMenu getByWeight(Long bookId, Integer weight);

    /**
     * 获取最大的菜单权重
     *
     * @param bookId
     * @return
     */
    int getMaxWeight(Long bookId);

    /**
     * 新增或更新（以title为准）菜单或内容
     *
     * @param bookId
     * @param title
     * @param desc
     * @param weight
     * @param content
     * @param transText
     * @param annotation
     * @return
     */
    BookContentVo addOrUpdate(Long bookId, String title, String desc, Integer weight, String content, String transText, String annotation);

    /**
     * 根据标题获取
     * @param bookId
     * @param title
     * @return
     */
    GjBookMenu getByTitle(Long bookId, String title);

}
