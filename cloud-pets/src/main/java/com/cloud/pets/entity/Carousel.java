package com.cloud.pets.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author xujiping
 * @since 2019-01-25
 */
public class Carousel extends Model<Carousel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 主题：home首页
     */
    private String subject;
    /**
     * 轮播图片路径json
     */
    private String url;
    /**
     * 跳转
     */
    private String skip;
    private Date createTime;
    /**
     * 状态
     */
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Carousel{" +
        "id=" + id +
        ", subject=" + subject +
        ", url=" + url +
        ", skip=" + skip +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
