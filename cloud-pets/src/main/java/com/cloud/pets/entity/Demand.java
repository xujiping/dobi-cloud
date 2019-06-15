package com.cloud.pets.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 需求表
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
public class Demand extends Model<Demand> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 需求类别ID
     */
    private Integer typeId;
    /**
     * 类别名称
     */
    private String typeName;
    /**
     * 发布时间
     */
    private Date pubTime;
    /**
     * 发布人ID
     */
    private String userId;
    /**
     * 宠物ID
     */
    private Integer petsId;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 宠物类别ID
     */
    private Integer categoryId;
    /**
     * 宠物品种ID
     */
    private Integer speciesId;
    /**
     * 内容详情
     */
    private String content;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Integer speciesId) {
        this.speciesId = speciesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPetsId() {
        return petsId;
    }

    public void setPetsId(Integer petsId) {
        this.petsId = petsId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", pubTime=" + pubTime +
                ", userId=" + userId +
                ", petsId=" + petsId +
                ", age=" + age +
                ", price=" + price +
                ", sex=" + sex +
                ", categoryId=" + categoryId +
                ", speciesId=" + speciesId +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
