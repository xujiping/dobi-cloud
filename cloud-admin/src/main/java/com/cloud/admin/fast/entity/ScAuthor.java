package com.cloud.admin.fast.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 诗词-作者
 * </p>
 *
 * @author xujiping
 * @since 2020-01-18
 */
public class ScAuthor extends Model<ScAuthor> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 朝代
     */
    private String dynasty;
    /**
     * 姓名
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    private Date createTime;
    private Date updateTime;
    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        return "ScAuthor{" +
        "id=" + id +
        ", dynasty=" + dynasty +
        ", name=" + name +
        ", desc=" + desc +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        "}";
    }
}
