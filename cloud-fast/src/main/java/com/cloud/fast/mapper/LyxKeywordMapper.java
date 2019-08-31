package com.cloud.fast.mapper;

import com.cloud.fast.entity.LyxKeyword;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 关键词表 Mapper 接口
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
public interface LyxKeywordMapper extends BaseMapper<LyxKeyword> {

    @Update("update lyx_keyword set use_count=use_count+1, use_total=use_total+1 where id=#{id}")
    Integer addCount(Long id);

    @Update("update lyx_keyword set use_count=use_count-1 where id=#{id}")
    Integer minusCount(Long id);

    @Update("update lyx_keyword set use_count=use_count+1, use_total=use_total+1 where id in (#{id})")
    Integer addCounts(String ids);

    @Update("update lyx_keyword set use_count=use_count-1 where id in (#{id})")
    Integer minusCounts(String ids);
}
