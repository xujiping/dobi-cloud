package com.cloud.admin.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.fast.entity.GjAuthor;
import com.cloud.admin.fast.entity.GjBookContent;
import com.cloud.admin.fast.entity.GjBookMenu;
import com.cloud.admin.fast.entity.vo.BookContentVo;
import com.cloud.admin.fast.entity.vo.GjBookVo;
import com.cloud.admin.fast.mapper.GjBookContentMapper;
import com.cloud.admin.fast.service.GjBookContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.admin.fast.service.GjBookMenuService;
import com.cloud.admin.fast.service.GjBookService;
import com.cloud.base.constants.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 古籍-书籍内容 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@Service
public class GjBookContentServiceImpl extends ServiceImpl<GjBookContentMapper, GjBookContent> implements GjBookContentService {

    @Autowired
    private GjBookService bookService;

    @Autowired
    private GjBookMenuService bookMenuService;

    @Override
    public BookContentVo getByMenuId(Long menuId) {
        if (menuId == null) {
            return null;
        }
        Wrapper<GjBookContent> wrapper = new EntityWrapper<>();
        wrapper.eq("menu_id", menuId);
        wrapper.eq("status", Constants.STATUS_NORMAL);
        return wrapper(selectOne(wrapper));
    }

    @Override
    public BookContentVo wrapper(GjBookContent bookContent) {
        BookContentVo contentVo = new BookContentVo();
        if (bookContent == null) {
            return contentVo;
        }
        BeanUtils.copyProperties(bookContent, contentVo);
        Long bookId = bookContent.getBookId();
        Long menuId = bookContent.getMenuId();
        // 书籍
        GjBookVo bookVo = bookService.get(bookId);
        if (bookVo != null) {
            contentVo.setBookTitle(bookVo.getBookName());
            GjAuthor author = bookVo.getGjAuthor();
            contentVo.setAuthor(author.getName());
        }
        // 菜单
        GjBookMenu bookMenu = bookMenuService.selectById(menuId);
        if (bookMenu != null) {
            Integer weight = bookMenu.getWeight();
            if (weight != null) {
                GjBookMenu preMenu = bookMenuService.getByWeight(bookId, weight - 1);
                if (preMenu != null) {
                    contentVo.setPreMenuId(preMenu.getId());
                }
                GjBookMenu nextMenu = bookMenuService.getByWeight(bookId, weight + 1);
                if (nextMenu != null) {
                    contentVo.setNextMenuId(nextMenu.getId());
                }
            }
            contentVo.setMenuTitle(bookMenu.getTitle());
        }
        return contentVo;
    }
}
