package com.cloud.admin.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xujiping
 * @Date: 2019年7月5日 0005 上午 09:56:09
 * @Version 1.0
 */
@Data
public class PermissionTree implements Serializable {

    private static final long serialVersionUID = 1098139921365010900L;

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 平台ID
     */
    private Integer platformId;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单值
     */
    private String value;

    /**
     * 是否叶子节点
     */
    private boolean leaf;

    private List<PermissionTree> children;
}
