package com.cloud.admin.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.fast.entity.GjBookContent;
import com.cloud.admin.fast.mapper.GjBookContentMapper;
import com.cloud.admin.fast.service.GjBookContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
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

    @Override
    public GjBookContent getByMenuId(Long menuId) {
        if (menuId == null) {
            return null;
        }
        Wrapper<GjBookContent> wrapper = new EntityWrapper<>();
        wrapper.eq("menu_id", menuId);
        wrapper.eq("status", Constants.STATUS_NORMAL);
        return selectOne(wrapper);
    }
}
