package com.cloud.fast.service;

import com.cloud.fast.entity.GjBook;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.fast.entity.vo.GjBookVo;

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

}
