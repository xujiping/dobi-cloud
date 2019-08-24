package com.cloud.fast.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动报名实体DTO
 *
 * @author xujiping
 * @date 2019-08-02 10:49
 */
@ApiModel("活动报名实体DTO")
@Data
public class SignActivityDto implements Serializable {

    private static final long serialVersionUID = 8934166923069192891L;
    /**
     * 标题
     */
    private String title;
    /**
     * 主题
     */
    private String subject;
    /**
     * 详情，富文本
     */
    private String desc;
    /**
     * 人数限制，负数代表无限制
     */
    private Integer maxPeople;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 报名开始时间
     */
    private String signStartTime;
    /**
     * 报名结束时间
     */
    private String signEndTime;
    /**
     * 地点
     */
    private String location;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 详细地址
     */
    private String locationDetail;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系电话，多个用半角逗号分隔
     */
    private String contactPhone;
    /**
     * 提交表单ID
     */
    private Integer formId;

    @ApiModelProperty("封面列表，多个用半角逗号分隔")
    private String covers;

    @ApiModelProperty("图片列表，多个用半角逗号分隔")
    private String images;

}
