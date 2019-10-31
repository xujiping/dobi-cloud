package com.gateway.cloud.client;

import com.gateway.cloud.common.Result;
import com.gateway.cloud.common.ResultCode;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xujiping
 * @date 2019-10-31 10:15
 */
@Component
@Slf4j
public class UserCenterFactory implements FallbackFactory<UserCenterClient> {
    @Override
    public UserCenterClient create(Throwable throwable) {
        return new UserCenterClient() {
            @Override
            public Result userInfo(String ucToken) {
                log.error("查询用户信息失败");
                return Result.failure(ResultCode.FAIL);
            }
        };
    }
}
