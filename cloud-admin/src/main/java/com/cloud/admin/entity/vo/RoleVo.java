package com.cloud.admin.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xujiping
 * @Date: 2019年7月3日 0003 上午 11:05:12
 * @Version 1.0
 */
@Data
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 8571979663698056179L;

    private Integer id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 简介
     */
    private String roleIntro;
    /**
     * 平台ID
     */
    private Integer platformId;

    private String platform;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 状态：0不可用 1正常
     */
    private Integer status;
}
