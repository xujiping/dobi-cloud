package com.cloud.pets.entity.vo;

/**
 * @author xujiping
 * @date 2019-01-29 15:32
 */
public class PetsVo {

    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 类别ID
     */
    private Integer categoryId;
    /**
     * 品种ID
     */
    private Integer speciesId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别   1： 男     2： 女     3：保密
     */
    private Integer sex;
    /**
     * 创建日期
     */
    private String createTime;
    /**
     * 更新日期
     */
    private String updateTime;
    /**
     * 是否存活    1：是    2：否
     */
    private Integer survival;

    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSurvival() {
        return survival;
    }

    public void setSurvival(Integer survival) {
        this.survival = survival;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PetsVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", speciesId=" + speciesId +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", survival=" + survival +
                ", image='" + image + '\'' +
                '}';
    }
}
