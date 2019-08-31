package com.cloud.fast.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.base.constants.StatusEnum;
import com.cloud.fast.entity.LyxRecommend;
import com.cloud.fast.entity.vo.LyxRecommendVo;
import com.cloud.fast.mapper.LyxRecommendMapper;
import com.cloud.fast.service.LyxRecommendService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<LyxRecommendVo> listByPage(Page<LyxRecommend> page) {
        Page<LyxRecommendVo> newPage = new Page<>();
        Wrapper<LyxRecommend> wrapper = new EntityWrapper<>();
        wrapper.eq("status", StatusEnum.NORMAL);
        page = selectPage(page);
        List<LyxRecommendVo> list;
        List<LyxRecommend> records = page.getRecords();
        if (records != null && records.size() > 0){
            list = records.stream().map(this::wrapper).collect(Collectors.toList());
            newPage.setRecords(list);
            newPage.setTotal(selectCount(wrapper));
        }
        return newPage;
    }

    @Override
    public LyxRecommendVo wrapper(LyxRecommend lyxRecommend) {
        LyxRecommendVo recommendVo = new LyxRecommendVo();
        if (lyxRecommend == null){
            return recommendVo;
        }
        BeanUtils.copyProperties(lyxRecommend, recommendVo);
        return recommendVo;
    }
}
