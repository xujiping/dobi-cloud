package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.fast.entity.LyxKeyword;
import com.cloud.fast.mapper.LyxKeywordMapper;
import com.cloud.fast.service.LyxKeywordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关键词表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class LyxKeywordServiceImpl extends ServiceImpl<LyxKeywordMapper, LyxKeyword> implements LyxKeywordService {

    @Autowired private LyxKeywordMapper keywordMapper;

    @Override
    public LyxKeyword getByName(String name) {
        if (StrUtil.isBlank(name)){
            return null;
        }
        Wrapper<LyxKeyword> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        LyxKeyword keyword = selectOne(wrapper);
        if (keyword == null){
            // 新增
            keyword = new LyxKeyword();
            keyword.setName(name);
            boolean insert = insert(keyword);
            if (!insert){
                return null;
            }
        }
        return keyword;
    }

    @Override
    public void updateCount(Long id, boolean add) {
        if (add){
            keywordMapper.addCount(id);
        }else{
            keywordMapper.minusCount(id);
        }
    }

    @Override
    public void updateCount(String ids, boolean add) {
        if (StrUtil.isNotBlank(ids)){
            if (add){
                keywordMapper.addCounts(ids);
            }else{
                keywordMapper.minusCounts(ids);
            }
        }
    }
}
