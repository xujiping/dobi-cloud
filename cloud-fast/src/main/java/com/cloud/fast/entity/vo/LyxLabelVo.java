package com.cloud.fast.entity.vo;

import com.cloud.fast.entity.LyxKeyword;
import lombok.Data;

import java.util.List;

/**
 * @Author: xujiping
 * @Date: 2019年8月29日 0029 下午 05:26:31
 * @Version 1.0
 */
@Data
public class LyxLabelVo {

    private Long id;
    /**
     * 推荐名称
     */
    private String name;
    /**
     * 主题
     */
    private String subject;
    /**
     * 喜欢数
     */
    private Integer likeCount;
    /**
     * 不喜欢数
     */
    private Integer dislikeCount;
    /**
     * 标签ID列表，用逗号分隔
     */
    private List<LyxKeyword> keywords;

    private Boolean liked;
}
