package com.cloud.admin.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xujiping
 * @Date: 2019年6月6日 0006 上午 11:31:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class UserVo implements Serializable {

    private static final long serialVersionUID = -6244014622146041114L;
    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("用户中心TOKEN")
    private String token;
}
