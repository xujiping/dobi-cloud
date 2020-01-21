package com.cloud.admin.fast.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 诗词
 * </p>
 *
 * @author xujiping
 * @since 2020-01-18
 */
public class ScPoetry extends Model<ScPoetry> {

    private static final long serialVersionUID = 1L;

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
    /**
     * 诗词
     */
    private String paragraphs;
    /**
     * 笔记
     */
    private String notes;
    /**
     * 标签
     */
    private String tags;
    private Date createTime;
    private Date updateTime;
    /**
     * 状态：0不可用 1正常
     */
    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRhythmic() {
        return rhythmic;
    }

    public void setRhythmic(String rhythmic) {
        this.rhythmic = rhythmic;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
        return "ScPoetry{" +
        "id=" + id +
        ", type=" + type +
        ", dynasty=" + dynasty +
        ", author=" + author +
        ", title=" + title +
        ", chapter=" + chapter +
        ", section=" + section +
        ", rhythmic=" + rhythmic +
        ", paragraphs=" + paragraphs +
        ", notes=" + notes +
        ", tags=" + tags +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        "}";
    }
}
