package com.cloud.pets.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 宠物品种详情
 * </p>
 *
 * @author xujiping
 * @since 2019-06-24
 */
public class PetsSpeciesDetail extends Model<PetsSpeciesDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 品种ID
     */
    private Integer id;
    /**
     * 简介
     */
    private String intro;
    /**
     * 详细介绍
     */
    private String details;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
        return "PetsSpeciesDetail{" +
        "id=" + id +
        ", intro=" + intro +
        ", details=" + details +
        ", createTime=" + createTime +
        "}";
    }
}
