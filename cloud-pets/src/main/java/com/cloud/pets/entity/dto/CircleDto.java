package com.cloud.pets.entity.dto;

import java.util.Date;

/**
 * @author xujiping
 * @date 2019-01-22 13:53
 */
public class CircleDto {

    private Integer id;
    /**
     * 发布人名称
     */
    private String pubUserName;
    /**
     * 主题：视频、图文
     */
    private String subject;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 封面JSON
     */
    private String cover;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPubUserName() {
        return pubUserName;
    }

    public void setPubUserName(String pubUserName) {
        this.pubUserName = pubUserName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CircleDto{" +
                "id=" + id +
                ", pubUserName='" + pubUserName + '\'' +
                ", subject='" + subject + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", cover='" + cover + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
