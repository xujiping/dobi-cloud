package com.cloud.pets.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujiping
 * @date 2019-01-25 14:04
 */
@Data
@ApiModel("轮播图")
public class CarouselVo implements Serializable {

    private static final long serialVersionUID = -7625849667443217719L;
    /**
     * 图片完整链接
     */
    @ApiModelProperty("图片URL")
    private String url;

    /**
     * 跳转
     */
    @ApiModelProperty("跳转")
    private String skip;

}
