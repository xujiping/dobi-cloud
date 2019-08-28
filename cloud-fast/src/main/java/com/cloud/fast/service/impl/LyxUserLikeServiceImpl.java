package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.LyxLabel;
import com.cloud.fast.entity.LyxUserLike;
import com.cloud.fast.mapper.LyxUserLikeMapper;
import com.cloud.fast.service.LyxLabelService;
import com.cloud.fast.service.LyxUserLikeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户喜欢收藏的 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
@Service
public class LyxUserLikeServiceImpl extends ServiceImpl<LyxUserLikeMapper, LyxUserLike> implements LyxUserLikeService {

    @Autowired private LyxLabelService lyxLabelService;

    @Override
    public boolean like(String userId, Long labelId, String keywords) {
        if (StrUtil.isBlank(userId) || labelId == null) {
            return false;
        }
        LyxLabel lyxLabel = lyxLabelService.selectById(labelId);
        if (lyxLabel == null){
            throw new BusinessException(ReturnCode.LABEL_NOT_EXIST);
        }
        LyxUserLike lyxUserLike = new LyxUserLike();
        lyxUserLike.setUserId(userId);
        lyxUserLike.setLabelId(labelId);
        lyxUserLike.setKeywords(keywords);
        return insert(lyxUserLike);
    }
}
