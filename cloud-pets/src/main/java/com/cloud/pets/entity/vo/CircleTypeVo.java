package com.cloud.pets.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xujiping
 * @Date: 2019年6月18日 0018 下午 02:51:58
 * @Version 1.0
 */
@Data
@ApiModel("圈子类型")
public class CircleTypeVo implements Serializable {

    private static final long serialVersionUID = -5417226856785859081L;

    private Integer id;
    /**
     * 名称
     */
    private String name;
}
