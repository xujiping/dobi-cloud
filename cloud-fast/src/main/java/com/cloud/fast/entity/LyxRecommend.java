package com.cloud.fast.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 推荐表
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
public class LyxRecommend extends Model<LyxRecommend> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 推荐标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 标签ID列表，逗号分隔
     */
    private String labelIds;
    /**
     * 创建者ID
     */
    private String createBy;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
        return "LyxRecommend{" +
        "id=" + id +
        ", title=" + title +
        ", desc=" + desc +
        ", labelIds=" + labelIds +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        "}";
    }
}
