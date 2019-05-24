package com.cloud.pets.entity.dto;

import java.math.BigDecimal;

/**
 * @author xujiping
 * @date 2019-01-25 18:19
 */
public class DemandDto {

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
    private String pubTime;
    /**
     * 发布人ID
     */
    private Integer userId;
    private Integer petsId;
    /**
     * 发布人名称
     */
    private String username;
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
     * 图片路径
     */
    private String image;

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

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getPetsId() {
        return petsId;
    }

    public void setPetsId(Integer petsId) {
        this.petsId = petsId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "DemandDto{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", pubTime=" + pubTime +
                ", userId=" + userId +
                ", petsId=" + petsId +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", price=" + price +
                ", sex=" + sex +
                ", categoryId=" + categoryId +
                ", speciesId=" + speciesId +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
