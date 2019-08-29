package com.cloud.fast.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: xujiping
 * @Date: 2019年8月29日 0029 下午 04:32:12
 * @Version 1.0
 */
@Data
public class LyxRecommendVo {

    private Long id;
    /**
     * 推荐标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;
    /**
     * 标签ID列表，逗号分隔
     */
    private String labelIds;
    /**
     * 创建者ID
     */
    private String createBy;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 状态
     */
    private Integer status;
}
