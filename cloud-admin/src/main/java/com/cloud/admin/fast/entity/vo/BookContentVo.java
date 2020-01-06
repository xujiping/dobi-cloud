package com.cloud.admin.fast.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xujiping
 * @Date: 2019年12月21日 0021 下午 03:09:22
 * @Version 1.0
 */
@Data
public class BookContentVo implements Serializable {

    private static final long serialVersionUID = -8465773657545471099L;

    private Long id;
    /**
     * 书籍ID
     */
    private Long bookId;

    private String bookTitle;

    private String author;
    /**
     * 目录ID
     */
    private Long menuId;

    private String menuTitle;

    private String desc;
    /**
     * 正文
     */
    private String content;
    /**
     * 译文
     */
    private String transText;
    /**
     * 注释
     */
    private String annotation;

    private Long preMenuId;

    private Long nextMenuId;

    private Integer weight;
}
