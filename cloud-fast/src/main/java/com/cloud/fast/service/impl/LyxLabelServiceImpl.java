package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.LyxLabel;
import com.cloud.fast.mapper.LyxLabelMapper;
import com.cloud.fast.service.LyxKeywordService;
import com.cloud.fast.service.LyxLabelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.fast.service.LyxUserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired private LyxKeywordService lyxKeywordService;

    @Autowired private LyxUserLikeService lyxUserLikeService;

    @Override
    public String add(String userId, String labels) {
        StringBuilder ids = new StringBuilder();
        if (StrUtil.isBlank(labels)){
            throw new BusinessException(ReturnCode.LYX_LABEL_NULL);
        }
        JSONArray jsonArray = JSONArray.parseArray(labels);
        if (jsonArray == null || jsonArray.size() <= 0){
            throw new BusinessException(ReturnCode.LYX_LABEL_NULL);
        }
        for (Object object: jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            LyxLabel lyxLabel = JSONObject.toJavaObject(jsonObject, LyxLabel.class);
            Long labelId = lyxLabel.getId();
            String keywords = lyxLabel.getKeywords();
            if (insert(lyxLabel)){
                ids.append(labelId).append(",");
                lyxKeywordService.updateCount(keywords, true);
                lyxUserLikeService.like(userId, labelId, keywords);
            }
        }
        return StrUtil.removeSuffix(ids.toString(), ",");
    }
}
