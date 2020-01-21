package com.cloud.admin.fast.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xujiping
 * @Date: 2020年1月19日 0019 下午 02:38:25
 * @Version 1.0
 */
@Data
public class ScPoetryVo implements Serializable {

    private static final long serialVersionUID = 3267482645368495207L;

    private String id;
    /**
     * 类型
     */
    private String type;
    /**
     * 朝代
     */
    private String dynasty;
    /**
     * 作者
     */
    private String author;

    private String titleJt;
    /**
     * 标题
     */
    private String title;
    /**
     * 章节
     */
    private String chapter;
    /**
     * 章节
     */
    private String section;
    /**
     * 韵律
     */
    private String rhythmic;

    private List<String> paragraphsJt;
    /**
     * 诗词
     */
    private List<String> paragraphs;
    /**
     * 笔记
     */
    private String notes;
    /**
     * 标签
     */
    private String tags;
}
