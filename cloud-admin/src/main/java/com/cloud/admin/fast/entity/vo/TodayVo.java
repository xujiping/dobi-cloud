package com.cloud.admin.fast.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xujiping
 * @date 2019-12-26 05:14
 */
@Data
public class TodayVo implements Serializable {

    private Integer id;
    /**
     * 书名
     */
    private String book;
    private Long bookId;
    private String author;
    /**
     * 封面图片
     */
    private String image;
    /**
     * 文字颜色
     */
    private String fontColor;
    /**
     * 内容
     */
    private List<String> contents;
    private String createTime;
    /**
     * 状态
     */
    private Integer status;
}
