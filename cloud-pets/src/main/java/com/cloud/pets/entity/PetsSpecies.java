package com.cloud.pets.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 宠物品种表
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
public class PetsSpecies extends Model<PetsSpecies> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;

    /**
     * 简介
     */
    private String intro;
    /**
     * 类别ID
     */
    private Integer categoryId;
    /**
     * 发情期
     */
    private Integer heat;
    /**
     * 图片
     */
    private String image;
    /**
     * 创建时间
     */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PetsSpecies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", categoryId=" + categoryId +
                ", heat=" + heat +
                ", image='" + image + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                '}';
    }
}
