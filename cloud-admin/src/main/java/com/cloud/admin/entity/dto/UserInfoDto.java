package com.cloud.admin.entity.dto;

import lombok.Data;

/**
 * @author xujiping
 * @date 2019-08-16 18:07
 */
@Data
public class UserInfoDto {

    private String nickName;

    private Integer gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

}
