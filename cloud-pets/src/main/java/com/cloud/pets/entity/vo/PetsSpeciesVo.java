package com.cloud.pets.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回app的对象
 * @author xujiping
 * @date 2019-01-21 16:39
 */
@Data
public class PetsSpeciesVo implements Serializable {

    private static final long serialVersionUID = 9221354956497286696L;

    private Integer id;

    /**
     * 标题
     */
    private String name;

    private String intro;

    /**
     * 图片
     */
    private String image;

    /**
     * 值
     */
    private String value;

}
