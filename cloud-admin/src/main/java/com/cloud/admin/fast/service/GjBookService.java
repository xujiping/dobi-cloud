package com.cloud.admin.fast.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.admin.fast.entity.GjBook;
import com.cloud.admin.fast.entity.dto.BookDto;
import com.cloud.admin.fast.entity.vo.GjBookSimpleVo;
import com.cloud.admin.fast.entity.vo.GjBookVo;

/**
 * <p>
 * 古籍-书籍 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
public interface GjBookService extends IService<GjBook> {

    /**
     * 查询
     * @param bookId
     * @return
     */
    GjBookVo get(Long bookId);

    /**
     * 包装
     * @param book
     * @return
     */
    GjBookVo wrapper(GjBook book);

    /**
     * 简单包装
     * @param book
     * @return
     */
    GjBookSimpleVo wrapperSimple(GjBook book);

    /**
     * 新增书籍
     * @param bookDto
     * @return
     */
    GjBookVo getOrAdd(BookDto bookDto);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<GjBookSimpleVo> page(Page<GjBook> page);

    /**
     * 查询
     * @param name
     * @return
     */
    GjBook get(String name);

}
