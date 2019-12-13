package com.cloud.admin.fast.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户活动列表
 * @Author: xujiping
 * @Date: 2019年8月18日 0018 上午 09:52:45
 * @Version 1.0
 */
@Data
public class UserApplyVo implements Serializable {

    private static final long serialVersionUID = -9036832743147589989L;

    private String userId;
    /**
     * 活动ID
     */
    private String activityId;
    /**
     * 是否已参加
     */
    private Boolean joined;

    private String title;

    private String desc;

    private String time;

    private String location;

    private String status;

}
