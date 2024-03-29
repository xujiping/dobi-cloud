package com.cloud.admin.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xujiping
 * @Date: 2019年6月6日 0006 下午 04:00:37
 * @Version 1.0
 */
@Data
public class PermissionVo implements Serializable {

    private static final long serialVersionUID = 2956812396699838275L;

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 平台ID
     */
    @ApiModelProperty("平台ID")
    private Integer platformId;
    /**
     * 菜单名
     */
    @ApiModelProperty("菜单名")
    private String menuName;
    /**
     * 菜单值
     */
    @ApiModelProperty("菜单值")
    private String menuValue;

    @ApiModelProperty("菜单编号")
    private Integer menuNo;

    @ApiModelProperty("权重")
    private Integer weight;

}
