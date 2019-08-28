package com.cloud.fast.service.impl;

import com.cloud.base.constants.StatusEnum;
import com.cloud.fast.entity.LyxRecommend;
import com.cloud.fast.mapper.LyxRecommendMapper;
import com.cloud.fast.service.LyxRecommendService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 推荐表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
@Service
public class LyxRecommendServiceImpl extends ServiceImpl<LyxRecommendMapper, LyxRecommend> implements LyxRecommendService {

    @Override
    public LyxRecommend add(String userId, String title, String desc) {
        Date date = new Date();
        LyxRecommend recommend = new LyxRecommend();
        recommend.setTitle(title);
        recommend.setDesc(desc);
        recommend.setCreateBy(userId);
        recommend.setCreateTime(date);
        recommend.setUpdateTime(date);
        recommend.setStatus(StatusEnum.NORMAL.getCode());
        return insert(recommend) ? recommend : null;
    }
}
