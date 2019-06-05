package com.cloud.pets.common.wx;

import lombok.Data;

import java.io.Serializable;

/**
 * 小程序通过wxCode获取sessionKey
 */
@Data
public class OauthByWxCode implements Serializable {

    private static final long serialVersionUID = -3572864940632801872L;

    /**
     * openid
     */
    private String openid;

    /**
     * 用户加密信息，需要解密
     */
    private String sessionKey;

}
