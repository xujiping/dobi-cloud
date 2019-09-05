package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.LyxLabel;
import com.cloud.fast.entity.LyxUserLike;
import com.cloud.fast.entity.vo.LyxLabelVo;
import com.cloud.fast.mapper.LyxLabelMapper;
import com.cloud.fast.service.LyxKeywordService;
import com.cloud.fast.service.LyxLabelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.fast.service.LyxUserLikeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐标签表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
@Service
public class LyxLabelServiceImpl extends ServiceImpl<LyxLabelMapper, LyxLabel> implements LyxLabelService {

    @Autowired
    private LyxKeywordService lyxKeywordService;

    @Autowired
    private LyxUserLikeService lyxUserLikeService;

    @Override
    public String add(String userId, String labels) {
        StringBuilder ids = new StringBuilder();
        if (StrUtil.isBlank(labels)) {
            throw new BusinessException(ReturnCode.LYX_LABEL_NULL);
        }
        JSONArray jsonArray = JSONArray.parseArray(labels);
        if (jsonArray == null || jsonArray.size() <= 0) {
            throw new BusinessException(ReturnCode.LYX_LABEL_NULL);
        }
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            LyxLabel lyxLabel = JSONObject.toJavaObject(jsonObject, LyxLabel.class);
            Long labelId = lyxLabel.getId();
            String keywords = lyxLabel.getKeywords();
            if (insert(lyxLabel)) {
                ids.append(labelId).append(",");
                lyxKeywordService.updateCount(keywords, true);
                lyxUserLikeService.like(userId, labelId, keywords);
            }
        }
        return StrUtil.removeSuffix(ids.toString(), ",");
    }

    @Override
    public List<LyxLabel> listByIds(String ids) {
        Wrapper<LyxLabel> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        return selectList(wrapper);
    }

    @Override
    public LyxLabelVo wrapper(LyxLabel lyxLabel, String userId) {
        LyxLabelVo labelVo = new LyxLabelVo();
        if (lyxLabel == null) {
            return labelVo;
        }
        Long labelId = lyxLabel.getId();
        BeanUtils.copyProperties(lyxLabel, labelVo);

        // 关键词
        labelVo.setKeywords(lyxKeywordService.listByIds(lyxLabel.getKeywords()));
        // 用户相关
        if (StrUtil.isNotBlank(userId)) {
            labelVo.setLiked(lyxUserLikeService.liked(userId, labelId));
        }
        return labelVo;
    }

    @Override
    public List<LyxLabel> listByUserLike(String userId, String subject) {
        List<LyxUserLike> likes = lyxUserLikeService.listByUser(userId);
        if (likes == null || likes.size()<=0){
            return null;
        }
        StringBuilder likeIds = new StringBuilder();
        Map<Long, String> map = new HashMap<>();
        for (LyxUserLike userLike : likes) {
            Long labelId = userLike.getLabelId();
            likeIds.append(labelId).append(",");
            map.put(labelId, userLike.getKeywords());
        }
        Wrapper<LyxLabel> wrapper = new EntityWrapper<>();
        wrapper.eq("subject", subject);
        wrapper.in("id", likeIds.toString());
        List<LyxLabel> list = selectList(wrapper);
        if (list != null && list.size() >0){
            for (LyxLabel label : list) {
                label.setKeywords(map.get(label.getId()));
            }
        }
        return list;
    }

}
