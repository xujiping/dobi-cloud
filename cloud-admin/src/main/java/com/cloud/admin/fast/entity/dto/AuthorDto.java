package com.cloud.admin.fast.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xujiping
 * @date 2019-11-12 14:35
 */
@ApiModel("作者DTO")
@Data
public class AuthorDto implements Serializable {

    private static final long serialVersionUID = -1482795695099994139L;

    private String name;
    /**
     * 字
     */
    private String zi;
    /**
     * 号
     */
    private String hao;
    /**
     * 笔名
     */
    private String biName;
    /**
     * 简介
     */
    private String introduce;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 死亡日期
     */
    private Date deathday;
    /**
     * 详细介绍
     */
    private String detail;
}
