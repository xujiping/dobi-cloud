package com.cloud.auth.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 用户中心配置
 * @Author: xujiping
 * @Date: 2019年6月6日 0006 下午 05:26:52
 * @Version 1.0
 */
@Configuration
@Data
public class UserCenterConfig {

    @Value("${uc.domain}")
    private String ucDomain;

    @Value("${uc.domain}${uc.request.user-info}")
    private String requestUser;

    @Value("${uc.domain}${uc.request.check-permission}")
    private String requestCheckPermission;

    @Value("${uc.domain}${uc.request.user-open-info}")
    private String requestUserOpenInfo;


}
