package com.gateway.cloud;

import com.gateway.cloud.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xujiping
 * @date 2019-10-29 17:55
 */
@FeignClient(value = "dobi-admin")
public interface UserCenterService {

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/sysUser/info")
    Result userInfo();
}
