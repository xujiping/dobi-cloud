package com.cloud.admin.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjAuthor;
import com.cloud.admin.fast.entity.dto.AuthorDto;
import com.cloud.admin.fast.mapper.GjAuthorMapper;
import com.cloud.admin.fast.service.GjAuthorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import org.springframework.beans.BeanUtils;
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
    public GjAuthor newAuthor(AuthorDto authorDto) {
        if (authorDto == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        GjAuthor author = new GjAuthor();
        BeanUtils.copyProperties(authorDto, author);
        if (insert(author)) {
            return get(author.getId());
        }
        return null;
    }

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

    @Override
    public GjAuthor get(Long authorId) {
        GjAuthor author = selectById(authorId);
        if (author == null || author.getStatus().equals(Constants.STATUS_UNENABLE)){
            return null;
        }
        return author;
    }

}
