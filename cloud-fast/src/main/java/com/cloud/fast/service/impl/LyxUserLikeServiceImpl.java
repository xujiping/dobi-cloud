package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.LyxLabel;
import com.cloud.fast.entity.LyxUserLike;
import com.cloud.fast.mapper.LyxUserLikeMapper;
import com.cloud.fast.service.LyxLabelService;
import com.cloud.fast.service.LyxUserLikeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new BusinessException(ResultCode.LABEL_NOT_EXIST);
        }
        LyxUserLike lyxUserLike = new LyxUserLike();
        lyxUserLike.setUserId(userId);
        lyxUserLike.setLabelId(labelId);
        lyxUserLike.setKeywords(keywords);
        return insert(lyxUserLike);
    }

    @Override
    public boolean dislike(String userId, Long labelId) {
        if (StrUtil.isBlank(userId) || labelId == null) {
            return false;
        }
        Wrapper<LyxUserLike> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("label_id", labelId);
        return delete(wrapper);
    }

    @Override
    public boolean liked(String userId, Long labelId) {
        if (StrUtil.isBlank(userId) || labelId == null) {
            return false;
        }
        Wrapper<LyxUserLike> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("label_id", labelId);
        return selectOne(wrapper) != null;
    }

    @Override
    public List<LyxUserLike> listByUser(String userId) {
        Wrapper<LyxUserLike> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        return selectList(wrapper);
    }
}
