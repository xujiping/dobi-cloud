package com.cloud.pets.entity.dto;

/**
 * 返回app的对象
 * @author xujiping
 * @date 2019-01-21 16:39
 */
public class PetsSpeciesDto {

    private Integer id;

    /**
     * 标题
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 值
     */
    private String value;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PetsSpeciesDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
