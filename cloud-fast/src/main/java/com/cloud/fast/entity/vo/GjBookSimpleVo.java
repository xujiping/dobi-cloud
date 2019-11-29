package com.cloud.fast.entity.vo;

import com.cloud.fast.entity.GjAuthor;
import com.cloud.fast.entity.GjCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: xujiping
 * @Date: 2019年11月19日 0019 下午 10:17:05
 * @Version 1.0
 */
@ApiModel("书籍")
@Data
public class GjBookSimpleVo implements Serializable {

    private static final long serialVersionUID = 2381337594574994339L;

    private Long id;

    /**
     * 书名
     */
    private String bookName;

    private String cover;
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
