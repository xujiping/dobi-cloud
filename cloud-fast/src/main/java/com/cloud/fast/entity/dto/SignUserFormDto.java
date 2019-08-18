package com.cloud.fast.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujiping
 * @date 2019-08-05 11:19
 */
@ApiModel("用户表单")
@Data
public class SignUserFormDto implements Serializable {

    private static final long serialVersionUID = 4171962610008999918L;

    /**
     * 活动ID
     */
    private String activityId;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别 1男 2女
     */
    private Integer sex;
}
