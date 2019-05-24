package com.cloud.pets.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 宠物图片表
 * </p>
 *
 * @author xujiping
 * @since 2019-01-29
 */
public class PetsImage extends Model<PetsImage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 宠物ID
     */
    private Long petsId;
    /**
     * 图片
     */
    private String image;
    private Date createTime;
    /**
     * 是否默认
     */
    private Boolean defaultImage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetsId() {
        return petsId;
    }

    public void setPetsId(Long petsId) {
        this.petsId = petsId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Boolean defaultImage) {
        this.defaultImage = defaultImage;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PetsImage{" +
        "id=" + id +
        ", petsId=" + petsId +
        ", image=" + image +
        ", createTime=" + createTime +
        ", defaultImage=" + defaultImage +
        "}";
    }
}
