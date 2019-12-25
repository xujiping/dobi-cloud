package com.cloud.admin.fast.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 每日推送
 * </p>
 *
 * @author xujiping
 * @since 2019-12-26
 */
public class GjToday extends Model<GjToday> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
    private String contents;
    private Date createTime;
    /**
     * 状态
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "GjToday{" +
        "id=" + id +
        ", book=" + book +
        ", bookId=" + bookId +
        ", author=" + author +
        ", image=" + image +
        ", fontColor=" + fontColor +
        ", contents=" + contents +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
