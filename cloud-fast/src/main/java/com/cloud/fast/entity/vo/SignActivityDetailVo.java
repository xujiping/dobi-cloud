package com.cloud.fast.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 活动详情
 *
 * @author xujiping
 * @date 2019-08-17 15:59
 */
@Data
@ApiModel("活动详情")
public class SignActivityDetailVo implements Serializable {

    private static final long serialVersionUID = 7875594334860641376L;

    private String id;
    /**
     * 用户ID
     */
    private String userId;
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
     * 图片json
     */
    private String images;
    /**
     * 人数限制，负数代表无限制
     */
    private Integer maxPeople;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
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
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系电话，多个用半角逗号分隔
     */
    private String contactPhone;

    private String wxNumber;
    /**
     * 提交表单ID
     */
    private Integer formId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建者用户ID
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    private Byte status;

    private String time;

    private Boolean signed;

    @ApiModelProperty("发起人头像")
    private String createHeader;

    @ApiModelProperty("参加人员的用户信息列表")
    private List<UserOpenInfoVo> signUsers;
}
