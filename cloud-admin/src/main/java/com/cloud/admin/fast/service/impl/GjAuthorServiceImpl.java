package com.cloud.admin.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.fast.entity.GjAuthor;
import com.cloud.admin.fast.mapper.GjAuthorMapper;
import com.cloud.admin.fast.service.GjAuthorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 古籍-作者 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@Service
public class GjAuthorServiceImpl extends ServiceImpl<GjAuthorMapper, GjAuthor> implements GjAuthorService {

    @Override
    public GjAuthor getOrAdd(String name) {
        if (StrUtil.isBlank(name)){
            return null;
        }
        Wrapper<GjAuthor> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        GjAuthor author = selectOne(wrapper);
        if (author == null){
            author = new GjAuthor();
            author.setName(name);
            if (!insert(author)){
                return null;
            }
        }
        return author;
    }
}
