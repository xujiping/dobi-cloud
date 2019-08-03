package com.cloud.fast.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xujiping
 * @date 2019-08-02 10:54
 */
@Data
public class SignFormDto implements Serializable {

    /**
     * 真实姓名
     */
    private Boolean name;
    /**
     * 手机号
     */
    private Boolean phone;
    /**
     * 年龄
     */
    private Boolean age;
    /**
     * 性别
     */
    private Boolean sex;
}
