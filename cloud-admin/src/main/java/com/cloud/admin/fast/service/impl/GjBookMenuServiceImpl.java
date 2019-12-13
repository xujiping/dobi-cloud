package com.cloud.admin.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.admin.fast.entity.GjBookMenu;
import com.cloud.admin.fast.mapper.GjBookMenuMapper;
import com.cloud.admin.fast.service.GjBookMenuService;
import com.cloud.base.constants.Constants;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 古籍-书籍目录表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@Service
public class GjBookMenuServiceImpl extends ServiceImpl<GjBookMenuMapper, GjBookMenu> implements GjBookMenuService {

    @Override
    public Page<GjBookMenu> page(Page<GjBookMenu> page) {
        if (page == null) {
            return null;
        }
        Wrapper<GjBookMenu> wrapper = new EntityWrapper<>();
//        Map<String, Object> condition = page.getCondition();
//        if (condition != null && condition.containsKey("bookId")) {
//            wrapper.eq("book_id", condition.get("bookId"));
//        }
        wrapper.eq("status", Constants.STATUS_NORMAL);
        wrapper.orderBy("weight");
        return selectPage(page, wrapper);
    }
}
