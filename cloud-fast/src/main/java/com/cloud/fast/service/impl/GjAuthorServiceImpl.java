package com.cloud.fast.service.impl;

import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.GjAuthor;
import com.cloud.fast.entity.dto.AuthorDto;
import com.cloud.fast.mapper.GjAuthorMapper;
import com.cloud.fast.service.GjAuthorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 古籍-作者 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
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
    public GjAuthor get(Long authorId) {
        GjAuthor author = selectById(authorId);
        if (author == null || author.getStatus().equals(Constants.STATUS_UNENABLE)){
            return null;
        }
        return author;
    }
}
