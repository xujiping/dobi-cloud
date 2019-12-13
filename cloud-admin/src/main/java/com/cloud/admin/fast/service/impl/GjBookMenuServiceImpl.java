package com.cloud.admin.fast.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.admin.fast.entity.GjBookMenu;
import com.cloud.admin.fast.mapper.GjBookMenuMapper;
import com.cloud.admin.fast.service.GjBookMenuService;
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
        return selectPage(page);
    }
}
