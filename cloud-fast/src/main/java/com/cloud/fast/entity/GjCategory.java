package com.cloud.fast.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 古籍-类别
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@Data
public class GjCategory extends Model<GjCategory> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 名称
     */
    private String name;
    private String parentId;
    /**
     * 级别
     */
    private Integer level;
    private Date createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GjCategory{" +
        "id=" + id +
        ", name=" + name +
        ", parentId=" + parentId +
        ", level=" + level +
        ", createTime=" + createTime +
        "}";
    }
}
