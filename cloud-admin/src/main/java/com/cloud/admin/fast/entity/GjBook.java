package com.cloud.admin.fast.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 古籍-书籍
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
public class GjBook extends Model<GjBook> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 书名
     */
    private String bookName;
    /**
     * 封面
     */
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
     * 类别ID
     */
    private Integer categoryId;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookIntroduce() {
        return bookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce;
    }

    public String getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(String bookDetail) {
        this.bookDetail = bookDetail;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GjBook{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", level=" + level +
        ", bookName=" + bookName +
        ", cover=" + cover +
        ", subheading=" + subheading +
        ", authorId=" + authorId +
        ", categoryId=" + categoryId +
        ", bookIntroduce=" + bookIntroduce +
        ", bookDetail=" + bookDetail +
        ", publishTime=" + publishTime +
        ", realPrice=" + realPrice +
        ", price=" + price +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        "}";
    }
}
