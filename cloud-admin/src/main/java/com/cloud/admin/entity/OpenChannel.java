package com.cloud.admin.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 开放渠道
 * </p>
 *
 * @author xujiping
 * @since 2019-08-16
 */
public class OpenChannel extends Model<OpenChannel> {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方渠道ID
     */
    private String id;
    /**
     * 渠道类型：1微信
     */
    private Integer type;
    /**
     * 微信uid
     */
    private String unionId;
    /**
     * 用户ID
     */
    private String userId;
    private Date createTime;
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OpenChannel{" +
        "id=" + id +
        ", type=" + type +
        ", unionId=" + unionId +
        ", userId=" + userId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
