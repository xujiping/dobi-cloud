package com.cloud.fast.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 古籍-书籍
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@Data
public class GjBook extends Model<GjBook> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long parentId;

    private Integer level;

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
    private Date createTime;
    private Date updateTime;
    /**
     * 状态
     */
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
