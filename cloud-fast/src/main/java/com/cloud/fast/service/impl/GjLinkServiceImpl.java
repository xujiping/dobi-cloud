package com.cloud.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.base.constants.Constants;
import com.cloud.fast.entity.GjLink;
import com.cloud.fast.mapper.GjLinkMapper;
import com.cloud.fast.service.GjLinkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 古籍-链接 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@Service
public class GjLinkServiceImpl extends ServiceImpl<GjLinkMapper, GjLink> implements GjLinkService {

    @Override
    public List<GjLink> listByBookId(Long bookId) {
        Wrapper<GjLink> wrapper = new EntityWrapper<>();
        wrapper.eq("book_id", bookId);
        wrapper.eq("status", Constants.STATUS_NORMAL);
        return selectList(wrapper);
    }
}
