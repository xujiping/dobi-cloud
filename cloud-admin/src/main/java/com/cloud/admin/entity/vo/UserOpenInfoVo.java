package com.cloud.admin.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xujiping
 * @Date: 2019年8月24日 0024 上午 11:40:31
 * @Version 1.0
 */
@ApiModel("用户开放信息")
@Data
public class UserOpenInfoVo implements Serializable {

    private static final long serialVersionUID = -4464056681110592782L;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;
}
