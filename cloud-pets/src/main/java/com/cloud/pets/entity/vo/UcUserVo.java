package com.cloud.pets.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 平台用户
 * </p>
 *
 * @author xujiping
 * @since 2019-06-05
 */
@Data
public class UcUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别：0未知 1男 2女
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 出生日期
     */
    private Long birthday;
    /**
     * 创建日期
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
