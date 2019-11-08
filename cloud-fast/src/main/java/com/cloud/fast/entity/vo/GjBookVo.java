package com.cloud.fast.entity.vo;

import com.cloud.fast.entity.GjAuthor;
import com.cloud.fast.entity.GjCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xujiping
 * @date 2019-11-08 11:40
 */
@Data
@ApiModel("书籍VO")
public class GjBookVo implements Serializable {

    private static final long serialVersionUID = 1522726681615961878L;

    private Long id;

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

    private GjAuthor gjAuthor;

    private GjCategory gjCategory;
}
