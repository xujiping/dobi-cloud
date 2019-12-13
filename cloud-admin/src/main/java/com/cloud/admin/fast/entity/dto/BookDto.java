package com.cloud.admin.fast.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xujiping
 * @date 2019-11-12 14:28
 */
@ApiModel("书籍DTO")
@Data
public class BookDto implements Serializable {

    private static final long serialVersionUID = 6522948714384787414L;

    private Long parentId;

    private Integer categoryId;
    /**
     * 书名
     */
    private String bookName;
    /**
     * 副标题
     */
    private String subheading;
    /**
     * 作者
     */
    private String authorId;
    /**
     * 书籍简介
     */
    private String bookIntroduce;
    /**
     * 书籍详情
     */
    private String bookDetail;
    /**
     * 出版时间
     */
    private Date publishTime;
    /**
     * 原价
     */
    private BigDecimal realPrice;
    /**
     * 优惠价格
     */
    private BigDecimal price;
}
