package com.cloud.fast.client;

import com.cloud.base.constants.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author xujiping
 * @date 2019-10-31 10:05
 */
@FeignClient(value = "dobi-admin", fallbackFactory = UserCenterFactory.class)
public interface UserCenterClient {

    @GetMapping("sysUser/info")
    Result userInfo(@RequestHeader(value = "ucToken") String ucToken);

}
